package com.cs203g1t2.springjwt.exception;

public class ItemCannotBeDeletedException extends RuntimeException {
    public ItemCannotBeDeletedException() {
        System.out.println("item id is null");
    }
}
