package com.cs203g1t2.springjwt.exception;

public class UserDetailEmptyException extends RuntimeException {
    public UserDetailEmptyException (String message) {
        super(message);
    }
}
