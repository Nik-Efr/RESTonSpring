package com.javarush.restonspring.exception;

public class DuplicateTitleException extends ApplicationException {
    private static final String ERROR_CODE = "40902"; // 409 Conflict + 02 (дубликат заголовка)

    public DuplicateTitleException(String title) {
        super("Topic with title '" + title + "' already exists", ERROR_CODE);
    }
}
