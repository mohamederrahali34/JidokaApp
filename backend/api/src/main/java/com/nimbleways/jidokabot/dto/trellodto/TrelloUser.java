package com.nimbleways.jidokabot.dto.trellodto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TrelloUser {
    private String id;
    private String username;
    private String fullName;

}
