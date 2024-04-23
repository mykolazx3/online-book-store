package com.mykola.onlinebookstore.exception;

public class SpecificationNotFoundException extends RuntimeException{
    public SpecificationNotFoundException(String message) {
        super(message);
    }
}
