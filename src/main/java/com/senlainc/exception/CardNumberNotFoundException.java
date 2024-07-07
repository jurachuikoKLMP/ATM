package com.senlainc.exception;

public class CardNumberNotFoundException extends RuntimeException {
    public CardNumberNotFoundException(String cardNumber) {
        super(String.format("Card number %s not found", cardNumber));
    }
}
