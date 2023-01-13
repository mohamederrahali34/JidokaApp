package com.nimbleways.jidokabot.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class StartCountingEvent extends ApplicationEvent {
    private int leadTime;
    private  String message;
    private String channel;
    private String cardId;

    public StartCountingEvent(final Object source,final String cardName,final int leadTime, final String message,final String channel) {
        super(source);
        this.leadTime = leadTime;
        this.message = message;
        this.channel =channel;
        this.cardId = cardName;
    }

    public StartCountingEvent(final Object source,final Clock clock) {
        super(source, clock);
    }

}
