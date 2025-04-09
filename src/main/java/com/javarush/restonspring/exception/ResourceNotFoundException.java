package com.javarush.restonspring.exception;

public class ResourceNotFoundException extends ApplicationException {

    private static final String ERROR_CODE = "40401"; // 404 (Not Found) + 01 (Resource Not Found)

    public ResourceNotFoundException(String message) {
        super(message, ERROR_CODE);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, ERROR_CODE, cause);
    }
}
