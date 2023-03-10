package com.cs203g1t2.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs203g1t2.springjwt.models.User;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  List<User> findTop5ByOrderByCarbonsavedDesc();

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  
}
