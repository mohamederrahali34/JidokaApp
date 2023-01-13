package com.nimbleways.jidokabot.services;

import com.nimbleways.jidokabot.dto.RuleDto;
import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.entities.Rule;
import com.nimbleways.jidokabot.entities.StockRule;
import com.nimbleways.jidokabot.entities.UsTimeRule;

import java.util.List;

public interface IRuleService {
    Rule saveRule(Rule rule);
    List<Rule> getRulesByBoard(Board board);
    List<UsTimeRule> getTimeRulesByBoardAndColumnName(Board board, String columnName);
    List<StockRule> getStockRulesByBoardAndColumnName(Board board, String columnName);
    Rule addRuleToBoard(RuleDto ruleDto);
    Rule updateRule(RuleDto ruleDto);
    void deleteRule(RuleDto ruleDto);
    Rule getRuleByTypeAndId(String type,String id);
    Rule toggleActivateRule(String id);
    List<UsTimeRule> findTimeRulesOfBoardByColumnNameAndType(Board board, String columnName, String type);
}
