package com.nimbleways.jidokabot.exceptions;

public record ErrorCode(String value) {
    public static ErrorCode from(final String value) {
        return new ErrorCode(value);
    }
}
