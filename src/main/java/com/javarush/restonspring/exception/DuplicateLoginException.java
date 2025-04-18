package com.javarush.restonspring.exception;

public class DuplicateLoginException extends ApplicationException {
    private static final String ERROR_CODE = "40301"; // 403 + 01 (логически связано с ошибкой доступа)

    public DuplicateLoginException(String login) {
        super("Login '" + login + "' already exists", ERROR_CODE);
    }
}
