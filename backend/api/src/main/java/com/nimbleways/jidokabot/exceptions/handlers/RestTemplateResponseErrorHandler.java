package com.nimbleways.jidokabot.exceptions.handlers;

import com.nimbleways.jidokabot.exceptions.WebhookAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.webjars.NotFoundException;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(final ClientHttpResponse httpResponse) throws IOException {
        return
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR;
    }

    @Override
    public void handleError(final ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode()
                .series() == SERVER_ERROR) {
            // handle SERVER_ERROR

        } else if (httpResponse.getStatusCode().series() ==CLIENT_ERROR) {
            // handle CLIENT_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.resolve(400)) {
                throw new WebhookAlreadyExistException();
            }
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException("ressource not found");
            }
        }
    }
}
