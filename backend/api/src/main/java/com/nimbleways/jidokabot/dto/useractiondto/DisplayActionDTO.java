package com.nimbleways.jidokabot.dto.useractiondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayActionDTO {
    private String translationKey;
    private ActionEntitiesDTO entities;

}
