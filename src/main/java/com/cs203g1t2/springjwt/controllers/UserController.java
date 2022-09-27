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
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("Unable to find user with id" + id);
        }

        return user.get();
    }

    @DeleteMapping(path = "/users/{Id}")
    public void deleteUserById(
            @PathVariable("Id") Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("User with id of " + id + " does not exist");
        }
        userRepository.deleteById(id);
        if (id == null)
            throw new RuntimeException();
    }

    @PutMapping(path = "/users/{Id}")
    public User updateUser(@PathVariable(value = "id") Long id,
            @RequestBody User newUser) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with id of " + id + " does not exist");
        }
        // return userRepository.findById(id).map(review -> {
        // review.setReview(newReview.getReview());
        // return reviews.save(review);
        // }).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        if (newUser == null) {
            throw new RuntimeException("User details Empty");
        }
        userRepository.findById(id).get().setUsername(newUser.getUsername());
        userRepository.findById(id).get().setPassword(newUser.getPassword());
        userRepository.findById(id).get().setEmail(newUser.getEmail());
        return userRepository.save(newUser);
    }
}