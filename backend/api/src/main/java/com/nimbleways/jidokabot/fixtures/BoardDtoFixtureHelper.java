package com.nimbleways.jidokabot.fixtures;

import com.nimbleways.jidokabot.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class BoardDtoFixtureHelper {

    @NotNull
    public static BoardDtoFixtureHelper.BoardDtoGenerator aBoardDto() {
        return new BoardDtoGenerator();
    }

    @NoArgsConstructor
    @With
    @AllArgsConstructor
    public static final class BoardDtoGenerator {
        private String id = UUID.randomUUID().toString();


        public BoardDto generate() {
            return new BoardDto(id);
        }
    }
}
