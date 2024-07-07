package com.senlainc.exception;

public class BalanceExceededException extends RuntimeException {
    public BalanceExceededException(){
        super("Balance exceeded");
    }
}
