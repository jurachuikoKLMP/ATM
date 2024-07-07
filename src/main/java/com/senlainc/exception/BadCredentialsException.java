package com.senlainc.exception;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(){
        super("Authentication error");
    }
}
