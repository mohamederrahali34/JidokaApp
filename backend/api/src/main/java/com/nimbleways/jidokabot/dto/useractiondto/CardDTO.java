package com.nimbleways.jidokabot.dto.useractiondto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private String type;
    private String idList;
    private String id;
    private String text;
    private String shortLink;

}
