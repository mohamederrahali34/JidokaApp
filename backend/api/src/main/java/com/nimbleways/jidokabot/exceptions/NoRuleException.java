package com.nimbleways.jidokabot.exceptions;

import org.springframework.http.HttpStatus;

public class NoRuleException extends BaseException {
    private static final ErrorCode ERROR_CODE = ErrorCode.from("no_rules");
    private static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;
    private static final ErrorMessage DEFAULT_MESSAGE = ErrorMessage.from("There is no rules");

    public NoRuleException() {
        super(ERROR_CODE, HTTP_STATUS, DEFAULT_MESSAGE, null);
    }
}
