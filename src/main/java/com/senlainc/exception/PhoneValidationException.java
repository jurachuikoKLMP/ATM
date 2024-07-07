package com.senlainc.exception;

public class PhoneValidationException extends RuntimeException {
    public PhoneValidationException(String phoneNumber) {
        super(String.format("Phone number %s is not valid", phoneNumber));
    }
}
