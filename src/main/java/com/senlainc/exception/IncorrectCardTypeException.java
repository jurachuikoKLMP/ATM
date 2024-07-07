package com.senlainc.exception;

import com.senlainc.entity.enums.CardType;

public class IncorrectCardTypeException extends RuntimeException {
    public IncorrectCardTypeException(CardType cardType){
        super(String.format("Card type %s is incorrect", cardType.toString()));
    }
}
