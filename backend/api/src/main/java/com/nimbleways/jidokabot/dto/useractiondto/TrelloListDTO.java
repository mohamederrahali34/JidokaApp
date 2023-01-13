package com.nimbleways.jidokabot.dto.useractiondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TrelloListDTO {
    private String type;
    private String id;
    private String text;
}
