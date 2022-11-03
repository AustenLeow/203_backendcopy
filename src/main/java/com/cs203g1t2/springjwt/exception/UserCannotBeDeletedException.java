package com.cs203g1t2.springjwt.exception;

public class UserCannotBeDeletedException extends RuntimeException {
    public UserCannotBeDeletedException() {
        System.out.println("User id is null");
    }
}
