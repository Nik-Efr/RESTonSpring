package com.javarush.restonspring.exception;

public class DataConflictException extends ApplicationException {

    private static final String ERROR_CODE = "40901"; // 409 (Conflict) + 01 (Data Conflict)

    public DataConflictException(String message) {
        super(message, ERROR_CODE);
    }

    public DataConflictException(String message, Throwable cause) {
        super(message, ERROR_CODE, cause);
    }
}
