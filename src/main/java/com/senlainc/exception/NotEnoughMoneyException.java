package com.senlainc.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(){
        super("Not enough money");
    }
}
