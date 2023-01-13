package com.nimbleways.jidokabot.fixtures;

import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.entities.Rule;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class RuleFixtureHelper {

    @NotNull
    public static RuleFixtureHelper.RuleGenerator aRule() {
        return new RuleFixtureHelper.RuleGenerator();
    }

    @NoArgsConstructor
    @With
    @AllArgsConstructor
    public static final class RuleGenerator {
        private UUID id = UUID.randomUUID();
        private String type = "duartion";
        private boolean active = false;
        private String message = "message";
        private String channel = "channel";
        private String workspace = "workspace";
        private Board board = BoardFixtureHelper.aBoard().generate();

        @NotNull
        public Rule generate() {
            return new Rule(id, message, channel, workspace, type, active, board);
        }

    }
}
