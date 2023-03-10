package com.cs203g1t2.springjwt.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.cs203g1t2.springjwt.repository.*;
import com.cs203g1t2.springjwt.exception.ItemCannotBeDeletedException;
import com.cs203g1t2.springjwt.exception.ItemNotFoundException;
import com.cs203g1t2.springjwt.exception.UserCannotBeDeletedException;
import com.cs203g1t2.springjwt.exception.UserDetailEmptyException;
import com.cs203g1t2.springjwt.exception.UserNotFoundException;
import com.cs203g1t2.springjwt.exception.WrongSecurityAnswerException;
import com.cs203g1t2.springjwt.models.*;
import java.util.Optional;
import lombok.*;
import com.cs203g1t2.springjwt.security.jwt.JwtUtils;
import com.cs203g1t2.springjwt.services.OrderService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartRepo;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/totalusers")
    public int getTotalNumOfUsers() {
        return userRepository.findAll().size();
    }

    @CrossOrigin
    @GetMapping("/users/{id}/carbonsaved")
    public BigDecimal getCarbonSaved(@PathVariable Long id) {
        // authentication = SecurityContextHolder.getContext().getAuthentication();
        // User user = userController.getLoggedInUser(authentication);
        User user = userRepository.findById(id).get();
        
        List<CartItem> orders = cartRepo.findByUserAndOrderIsNotNull(user);
        BigDecimal cs = new BigDecimal(0);
        for (CartItem o : orders) {
            cs = cs.add(o.getCarbontotal());
        }
        user.setCarbonsaved(cs);
        userRepository.save(user);
        return cs;
    }

    @GetMapping("/users/top5")
    public List<User> getTop5() {
        return userRepository.findTop5ByOrderByCarbonsavedDesc();
    }

    @CrossOrigin
    @GetMapping("/users/{id}/moneysaved")
    public BigDecimal getMoneySaved(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        
        List<CartItem> orders = cartRepo.findByUserAndOrderIsNotNull(user);
        BigDecimal cs = new BigDecimal(0);
        for (CartItem o : orders) {
            cs = cs.add(o.getAmountsaved());
        }
        user.setMoneysaved(cs);
        userRepository.save(user);
        return cs;
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!(user.isPresent())) {
            throw new UserNotFoundException("Unable to find user with id" + id);
        }
        return user.get();
    }

    @DeleteMapping(path = "/users/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(
            @PathVariable("Id") Long id) {
        if (!(userRepository.findById(id).isPresent())) {
            throw new UserNotFoundException("User with id of " + id + " does not exist");
        }
        userRepository.deleteById(id);
        if (id == null)
            throw new UserCannotBeDeletedException();
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id,
            @RequestBody User newUser) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id of " + id + " does not exist");
        }
        if (newUser == null) {
            throw new UserDetailEmptyException("User details Empty");
        }
        User theuser = userRepository.findById(id).get();
        if (newUser.getAnswer().equals(theuser.getAnswer())) {

            return userRepository.findById(id).map(user -> {
                user.setUsername(newUser.getUsername());
                user.setPassword(encoder.encode(newUser.getPassword()));
                user.setEmail(newUser.getEmail());
                user.setCarbonsaved(newUser.getCarbonsaved());
                user.setMoneysaved(newUser.getMoneysaved());
                user.setAnswer(newUser.getAnswer());
                return userRepository.save(user);
            }).orElseThrow(() -> new UserNotFoundException("item with id " + id + " does not exist"));
        } else {
            throw new WrongSecurityAnswerException("Wrong answer to security question");
        }
    }
}