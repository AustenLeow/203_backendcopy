package com.cs203g1t2.springjwt.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.cs203g1t2.springjwt.repository.*;
import com.cs203g1t2.springjwt.models.*;
import java.util.Optional;
import lombok.*;
import com.cs203g1t2.springjwt.security.jwt.JwtUtils;
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
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/carbonsavings")
    public Long getUsersCarbonsavings() {
        List<User> users= userRepository.findAll();
        Long total = 0L;
        for (User user : users) {
            total += user.getCarbonsaved();
        }
        return total;
    }

    @GetMapping("/users/moneysavings")
    public Long getUsersMoneysavings() {
        List<User> users= userRepository.findAll();
        Long total = 0L;
        for (User user : users) {
            total += user.getMoneysaved();
        }
        return total;
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!(user.isPresent())) {
            throw new RuntimeException("Unable to find user with id" + id);
        }

        return user.get();
    }

    @DeleteMapping(path = "/users/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(
            @PathVariable("Id") Long id) {
        if (!(userRepository.findById(id).isPresent())) {
            throw new RuntimeException("User with id of " + id + " does not exist");
        }
        userRepository.deleteById(id);
        if (id == null)
            throw new RuntimeException();
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id,
            @RequestBody User newUser) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with id of " + id + " does not exist");
        }
        if (newUser == null) {
            throw new RuntimeException("User details Empty");
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
            }).orElseThrow(() -> new RuntimeException());
        } else {
            throw new RuntimeException("Wrong answer to security question");
        }
    }
}