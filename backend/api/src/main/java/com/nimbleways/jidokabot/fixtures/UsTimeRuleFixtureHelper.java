package com.nimbleways.jidokabot.fixtures;

import com.nimbleways.jidokabot.entities.UsTimeRule;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.jetbrains.annotations.NotNull;

public class UsTimeRuleFixtureHelper {

    public static UsTimeRuleFixtureHelper.UsDurationRuleGenerator aUsTimeRule() {
        return new UsDurationRuleGenerator();

    }

    @NoArgsConstructor
    @With
    @AllArgsConstructor
    public static final class UsDurationRuleGenerator {
        private String columnName;
        private double duration;
        private String unit;

        @NotNull
        public UsTimeRule generate() {
            return new UsTimeRule();
        }
    }

}
