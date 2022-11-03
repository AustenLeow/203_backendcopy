package com.cs203g1t2.springjwt.exception;

public class WrongSecurityAnswerException extends RuntimeException {
    public WrongSecurityAnswerException(String message){
        super(message);
    }   
}
