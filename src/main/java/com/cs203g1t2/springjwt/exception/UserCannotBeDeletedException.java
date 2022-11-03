package com.cs203g1t2.springjwt.exception;

public class UserCannotBeDeletedException extends RuntimeException {
    public UserCannotBeDeletedException(Long id) {
        System.out.println("User with " + id + " not found");
    }
}
