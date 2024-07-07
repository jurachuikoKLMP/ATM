package com.senlainc.exception;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(long id) {
        super(String.format("Entity with id %s not found", id));
    }
}
