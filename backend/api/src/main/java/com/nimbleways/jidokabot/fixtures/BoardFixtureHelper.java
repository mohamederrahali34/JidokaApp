package com.nimbleways.jidokabot.fixtures;

import com.nimbleways.jidokabot.entities.Board;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class BoardFixtureHelper {

    @NotNull
    public static BoardFixtureHelper.BoardGenerator aBoard() {
        return new BoardGenerator();
    }

    @NoArgsConstructor
    @With
    @AllArgsConstructor
    public static final class BoardGenerator {
        private String id = UUID.randomUUID().toString();

        @NotNull
        public Board generate() {
            return new Board(id,null);
        }

    }


}
