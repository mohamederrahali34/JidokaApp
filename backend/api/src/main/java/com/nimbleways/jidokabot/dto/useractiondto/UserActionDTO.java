package com.nimbleways.jidokabot.dto.useractiondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActionDTO {
    private String id;
    private String idMemberCreator;
    private String type;
    private String date;
    private DisplayActionDTO display;

}
