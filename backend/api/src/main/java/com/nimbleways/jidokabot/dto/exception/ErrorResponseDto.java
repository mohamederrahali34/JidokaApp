package com.nimbleways.jidokabot.dto.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponseDto {
    private String errorCode;
    private String message;
    private int httpStatusCode;
    private LocalDateTime timestamp;
    private Object errorMetadata;
}
