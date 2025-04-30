package com.javarush.restonspring.exception;

public class DuplicateLoginException extends ApplicationException {
    private static final String ERROR_CODE = "403";

    public DuplicateLoginException(String login) {
        super("Login '" + login + "' already exists", ERROR_CODE);
    }
}
