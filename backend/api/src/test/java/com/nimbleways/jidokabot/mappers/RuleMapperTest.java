package com.nimbleways.jidokabot.mappers;

import com.nimbleways.jidokabot.dto.RuleDto;
import com.nimbleways.jidokabot.entities.StockRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RuleMapperTest {
    @Test
    public void testRuleDtoTOStockRule(){
        //GIVEN
        final RuleDto ruleDto = new RuleDto();
        ruleDto.setType("stock");
        final Map<String,String> params = new HashMap<>();
        params.put("stock","4");
        params.put("columnName","doing");
        params.put("owner","anyone");
        ruleDto.setParams(params);
        ruleDto.setChannel("jidokabot");
        ruleDto.setIdBoard(UUID.randomUUID().toString());
        ruleDto.setId(UUID.randomUUID().toString());
        //WHEN
        final StockRule actual = RulesMapper.INSTANCE.ruleDtoToStockRule(ruleDto);
        //test if rule dto is valid
        Assertions.assertNotNull(actual);
        //test mapping
        Assertions.assertEquals(4.0,actual.getNbCards());
    }
}
