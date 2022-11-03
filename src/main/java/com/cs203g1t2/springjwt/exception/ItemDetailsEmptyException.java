package com.cs203g1t2.springjwt.exception;

public class ItemDetailsEmptyException extends RuntimeException {
    public ItemDetailsEmptyException(String message) {
        super(message);
    }
}
