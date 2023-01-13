package com.nimbleways.jidokabot.exceptions;

import com.nimbleways.jidokabot.dto.exception.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

import static com.nimbleways.jidokabot.utils.ExceptionLoggingHelper.log;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the {@link BaseException} exceptions and returns a JSON formatted response.
     *
     * @param ex      : the ex
     * @param request : the request on which the ex occurred
     * @return a JSON formatted response containing the ex details and additional fields
     */
    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Object> handleBaseException(final BaseException ex, final ServletWebRequest request) {
        log(ex, request);
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode(ex.getErrorCode().value())
                .httpStatusCode(ex.getHttpStatus().value())
                .timestamp(LocalDateTime.now())
                .errorMetadata(ex.getErrorMetadata())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponseDto);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException ex, final ServletWebRequest request) {
        log(ex, request);
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode("access_denied")
                .httpStatusCode(HttpStatus.FORBIDDEN.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponseDto);
    }

    /**
     * Handles uncaught {@link Exception} exceptions and returns a JSON formatted response.
     *
     * @param ex      : the ex
     * @param request : the request on which the ex occurred
     * @return a JSON formatted response containing the ex details
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleUncaughtException(final Exception ex, final ServletWebRequest request) {
        log(ex, request);
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode("uncaught_exception")
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

}
