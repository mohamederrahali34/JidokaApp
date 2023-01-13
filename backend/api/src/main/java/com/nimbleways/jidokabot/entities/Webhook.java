package com.nimbleways.jidokabot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Webhook {

    private String id;
    private String callbackURL;
    private String idModel;
    private String consecutiveFailures;
    private String description;
    private String active;

}
