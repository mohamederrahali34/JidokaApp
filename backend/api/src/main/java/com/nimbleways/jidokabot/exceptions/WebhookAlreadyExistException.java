package com.nimbleways.jidokabot.exceptions;

import org.springframework.http.HttpStatus;

public class WebhookAlreadyExistException extends BaseException {
    private static final ErrorCode ERROR_CODE = ErrorCode.from("webhook_already_exist");
    private static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;
    private static final ErrorMessage DEFAULT_MESSAGE = ErrorMessage.from("a webhook with this token and model exist");

    public WebhookAlreadyExistException(final ErrorCode errorCode, final HttpStatus httpStatus, final ErrorMessage message) {
        super(errorCode, httpStatus, message);
    }

    public WebhookAlreadyExistException() {
        super(ERROR_CODE, HTTP_STATUS, DEFAULT_MESSAGE);
    }

    public WebhookAlreadyExistException(final ErrorCode errorCode, final HttpStatus httpStatus) {
        super(errorCode, httpStatus);
    }
}
