package com.nimbleways.jidokabot.mappers;

import com.nimbleways.jidokabot.dto.RuleDto;
import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.entities.StockRule;
import com.nimbleways.jidokabot.entities.UsTimeRule;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public class RulesMapper {
    public static final  RulesMapper INSTANCE = new RulesMapper();

    public UsTimeRule ruleDtoToTimeRule(final RuleDto ruleDto, final Board board) {
        final UsTimeRule usTimeRule = new UsTimeRule();
        usTimeRule.setBoard(board);
        final Map<String, String> params = ruleDto.getParams();
        usTimeRule.setType(ruleDto.getType());
        final boolean dtoIsvalid = !(params.isEmpty()) && params.get("duration")!=null && params.get("columnName")!=null && params.get("unit")!=null ;
        if(!dtoIsvalid){
           return null;

        }
        usTimeRule.setTimeRuleColumnName(params.get("columnName"));
        usTimeRule.setUnit(params.get("unit"));
        usTimeRule.setDuration(Double.parseDouble(params.get("duration")));
        usTimeRule.setChannel(ruleDto.getChannel());
        usTimeRule.setWorkspace(ruleDto.getWorkspace());
        usTimeRule.setActive(ruleDto.isActive());
        usTimeRule.setMessage(ruleDto.getMessage());
        return usTimeRule;
    }
    public StockRule ruleDtoToStockRule(final RuleDto ruleDto){
        final boolean dtoIsValid = !(ruleDto.getParams().isEmpty()) && ruleDto.getParams().get("columnName")!=null && ruleDto.getParams().get("owner")!=null &&  ruleDto.getParams().get("stock")!=null;
        if(!dtoIsValid){
            return null;
        }
        final StockRule stockRule = new StockRule();
        stockRule.setOwner(ruleDto.getParams().get("owner"));
        stockRule.setNbCards(Double.parseDouble(ruleDto.getParams().get("stock")));
        stockRule.setActive(ruleDto.isActive());
        stockRule.setBoard(new Board(ruleDto.getIdBoard()));
        stockRule.setChannel(ruleDto.getChannel());
        stockRule.setWorkspace(ruleDto.getWorkspace());
        stockRule.setMessage(ruleDto.getMessage());
        stockRule.setStockRuleColumnName(ruleDto.getParams().get("columnName"));
        stockRule.setType(ruleDto.getType());
        return stockRule;
    }
}
