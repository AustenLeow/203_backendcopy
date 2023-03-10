package com.cs203g1t2.springjwt.controllers;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.cs203g1t2.springjwt.enums.*;
import com.cs203g1t2.springjwt.exception.RoleNotFoundException;
import com.cs203g1t2.springjwt.exceptions.TokenRefreshException;
import com.cs203g1t2.springjwt.models.Role;
import com.cs203g1t2.springjwt.models.User;
import com.cs203g1t2.springjwt.models.RefreshToken;
import com.cs203g1t2.springjwt.payload.request.LoginRequest;
import com.cs203g1t2.springjwt.payload.request.SignupRequest;
import com.cs203g1t2.springjwt.payload.request.TokenRefreshRequest;
import com.cs203g1t2.springjwt.payload.response.JwtResponse;
import com.cs203g1t2.springjwt.payload.response.MessageResponse;
import com.cs203g1t2.springjwt.payload.response.TokenRefreshResponse;
import com.cs203g1t2.springjwt.repository.RoleRepository;
import com.cs203g1t2.springjwt.repository.UserRepository;
import com.cs203g1t2.springjwt.security.jwt.JwtUtils;
import com.cs203g1t2.springjwt.security.services.UserDetailsImpl;
import com.cs203g1t2.springjwt.security.services.RefreshTokenService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
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

  @Autowired
  RefreshTokenService refreshTokenService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();   

    String jwt = jwtUtils.generateJwtToken(userDetails);
    
    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

    return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(),userDetails.getId(),
    userDetails.getUsername(), userDetails.getEmail(),roles, userDetails.getAnswer()));
  }

  @GetMapping("/currentuser")
  public User getLoggedInUser(Authentication authentication) {
    authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return null;
    }
    // User user = null;
    Object principal = authentication.getPrincipal();
    User user = new User(((UserDetailsImpl)principal).getId(), ((UserDetailsImpl)principal).getUsername(), ((UserDetailsImpl)principal).getEmail(), ((UserDetailsImpl)principal).getPassword(),((UserDetailsImpl)principal).getCarbonsaved() , ((UserDetailsImpl)principal).getMoneysaved() , ((UserDetailsImpl)principal).getAnswer());
    return user;
  }

  @GetMapping("/currentuser/carbonsaved")
  public BigDecimal getLoggedInUserCarbon(Authentication authentication) {
    authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return null;
    }
    // User user = null;
    Object principal = authentication.getPrincipal();
    User user = new User(((UserDetailsImpl)principal).getId(), ((UserDetailsImpl)principal).getUsername(), ((UserDetailsImpl)principal).getEmail(), ((UserDetailsImpl)principal).getPassword(), ((UserDetailsImpl)principal).getCarbonsaved() , ((UserDetailsImpl)principal).getMoneysaved() , ((UserDetailsImpl)principal).getAnswer());
    return user.getCarbonsaved();
  }

  @GetMapping("/currentuser/moneysaved")
  public BigDecimal getLoggedInUserMoney(Authentication authentication) {
    authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return null;
    }
    // User user = null;
    Object principal = authentication.getPrincipal();
    User user = new User(((UserDetailsImpl)principal).getId(), ((UserDetailsImpl)principal).getUsername(), ((UserDetailsImpl)principal).getEmail(), ((UserDetailsImpl)principal).getPassword(), ((UserDetailsImpl)principal).getCarbonsaved() , ((UserDetailsImpl)principal).getMoneysaved() , ((UserDetailsImpl)principal).getAnswer());
    return user.getMoneysaved();
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()),
               BigDecimal.valueOf(0),BigDecimal.valueOf(0), encoder.encode(signUpRequest.getAnswer()));


    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "user":
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();

    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
            "Refresh token is not in database!"));
  }
  
  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long userId = userDetails.getId();
    refreshTokenService.deleteByUserId(userId);
    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
  }
}
