package com.nimbleways.jidokabot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleDto {
    private String id;
    private String idBoard;
    private boolean active;
    private String type;
    private Map<String, String> params = new HashMap<>();
    private String message;
    private String workspace;
    private String channel;
}
