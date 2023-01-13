package com.nimbleways.jidokabot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebhookDto {
    private String callback;
    private String description;
    private String idModel;
    private boolean active;
    private String key;
    private String token;
}
