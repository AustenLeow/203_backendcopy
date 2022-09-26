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
     public User getUser(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("Unable to find user with id" + id);
        }

        return user.get();
     }


    // @GetMapping("/users/{id}")
    // public User getUser(@PathVariable Long id) {
    //     Optional<User> user = users.findById(id);

    //     if (user.isEmpty()) {
    //         throw new RuntimeException("Unable to find user with Username " + id);
    //     }

    //     SecurityContextHolder.getContext().setAuthentication(authentication);
    //     String jwt = jwtUtils.generateJwtToken(authentication);

    //     return (new JwtResponse(jwt,
    //             user.get()));
    // }
}