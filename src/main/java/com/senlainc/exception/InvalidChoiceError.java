package com.senlainc.exception;

public class InvalidChoiceError extends RuntimeException {
    public InvalidChoiceError(int choice){
        super(String.format("There's no choice of %s", choice));
    }
}
