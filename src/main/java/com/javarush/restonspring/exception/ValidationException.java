package com.javarush.restonspring.exception;

public class ValidationException extends ApplicationException {

    private static final String ERROR_CODE = "400";

    public ValidationException(String message) {
        super(message, ERROR_CODE);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, ERROR_CODE, cause);
    }
}
