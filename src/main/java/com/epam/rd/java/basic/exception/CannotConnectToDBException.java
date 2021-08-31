package com.epam.rd.java.basic.exception;

public class CannotConnectToDBException extends RuntimeException {
    
    public CannotConnectToDBException(String message) {
        super(message);
    }
}
