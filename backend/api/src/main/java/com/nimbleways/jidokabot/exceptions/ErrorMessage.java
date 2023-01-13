package com.nimbleways.jidokabot.exceptions;

public record ErrorMessage(String value) {
    public static ErrorMessage from(final String value) {
        return new ErrorMessage(value);
    }

    public static ErrorMessage empty() {
        return from("");
    }
}
