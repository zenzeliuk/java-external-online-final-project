package com.epam.rd.java.basic.exception;

public class NotFoundCommandException extends RuntimeException{
    public NotFoundCommandException() {
        super();
    }

    public NotFoundCommandException(String message) {
        super(message);
    }

    public NotFoundCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}