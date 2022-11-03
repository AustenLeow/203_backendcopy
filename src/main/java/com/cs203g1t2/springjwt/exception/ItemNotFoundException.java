package com.cs203g1t2.springjwt.exception;


public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
