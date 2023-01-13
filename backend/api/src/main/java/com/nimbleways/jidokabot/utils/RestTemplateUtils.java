package com.nimbleways.jidokabot.utils;

import com.nimbleways.jidokabot.entities.Webhook;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class RestTemplateUtils {

    private final RestTemplate restTemplate;


    public Webhook postWebhook(final String url, final Object obj) {
        final HttpEntity<Object> objectHttpEntity = new HttpEntity<>(obj);
        return restTemplate.postForObject(url, objectHttpEntity, Webhook.class);
    }
    public <T> T post(final String url,final T obj,final Class<T> responseType){
        final HttpEntity<T> objectHttpEntity = new HttpEntity<>(obj);
        return restTemplate.postForObject(url, objectHttpEntity, responseType);
    }
    public <T> T get(final String url,final Class<T> responseType){
        return restTemplate.getForObject(url,responseType);
    }
}
