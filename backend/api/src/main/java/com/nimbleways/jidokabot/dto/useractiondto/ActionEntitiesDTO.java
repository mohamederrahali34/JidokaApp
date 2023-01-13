package com.nimbleways.jidokabot.dto.useractiondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionEntitiesDTO {
    private TrelloListDTO listAfter;
    private CardDTO card;
    private MemberCreatorDTO memberCreator;
}
