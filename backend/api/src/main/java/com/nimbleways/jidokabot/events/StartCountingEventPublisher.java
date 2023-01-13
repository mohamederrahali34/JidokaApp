package com.nimbleways.jidokabot.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StartCountingEventPublisher {

    private final  ApplicationEventPublisher applicationEventPublisher;

    public StartCountingEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishStartCountingEvent(final String cardId,final int leadTime, final String message,final String channel) {
        applicationEventPublisher.publishEvent(new StartCountingEvent(this,cardId, leadTime, message,channel));
    }

}
