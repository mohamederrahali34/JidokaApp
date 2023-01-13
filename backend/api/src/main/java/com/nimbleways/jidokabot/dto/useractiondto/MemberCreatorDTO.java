package com.nimbleways.jidokabot.dto.useractiondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreatorDTO {
    private String id;
    private String username;
    private String text;
}
