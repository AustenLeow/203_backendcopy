package com.cs203g1t2.springjwt.exception;

public class ItemCannotBeDeletedException extends RuntimeException {
    public ItemCannotBeDeletedException(Long id) {
        System.out.println("item with " + id + " not found");
    }
}
