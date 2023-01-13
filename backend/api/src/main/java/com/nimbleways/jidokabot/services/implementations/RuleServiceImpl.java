package com.nimbleways.jidokabot.services.implementations;

import com.nimbleways.jidokabot.dto.RuleDto;
import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.entities.Rule;
import com.nimbleways.jidokabot.entities.StockRule;
import com.nimbleways.jidokabot.entities.UsTimeRule;
import com.nimbleways.jidokabot.mappers.RulesMapper;
import com.nimbleways.jidokabot.repositories.RuleRepository;
import com.nimbleways.jidokabot.services.IBoardService;
import com.nimbleways.jidokabot.services.IRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RuleServiceImpl implements IRuleService {
    private final RuleRepository ruleRepository;
    private final IBoardService boardService;


    public RuleServiceImpl(final RuleRepository ruleRepository, final IBoardService boardService) {
        this.ruleRepository = ruleRepository;
        this.boardService = boardService;
    }

    @Override
    public Rule saveRule(final Rule rule) {
        return ruleRepository.save(rule);
    }


    @Override
    public List<Rule> getRulesByBoard(final Board board) {
        return ruleRepository.findByBoard(board);
    }

    @Override
    public List<UsTimeRule> getTimeRulesByBoardAndColumnName(final Board board, final String columnName) {
        return ruleRepository.getTimeRulesByBoardAndColumnName(board, columnName);
    }

    @Override
    public List<StockRule> getStockRulesByBoardAndColumnName(final Board board,final String columnName) {
        return ruleRepository.getStockRulesByBoardAndColumnName(board,columnName);
    }


    @Override
    public Rule addRuleToBoard(final RuleDto ruleDto) {
        Rule rule = null;
        final Board board = boardService.getBoardById(ruleDto.getIdBoard());
        if ("time".equals(ruleDto.getType())) {
                rule = RulesMapper.INSTANCE.ruleDtoToTimeRule(ruleDto, board);
                //test if the rule already exist
                final List<UsTimeRule> rules = getTimeRulesByBoardAndColumnName(board, ruleDto.getParams().get("columnName"));
                if (!rules.isEmpty()) {
                    rule.setId(rules.get(0).getId());
                }
                rule = saveRule(rule);
            }
        else if("stock".equals(ruleDto.getType())){
            rule = RulesMapper.INSTANCE.ruleDtoToStockRule(ruleDto);
            if(rule==null){
                return null;
            }
            final List<StockRule> stockRules = getStockRulesByBoardAndColumnName(board,ruleDto.getParams().get("columnName"));
            if(!stockRules.isEmpty()){
                rule.setId(stockRules.get(0).getId());
            }
            rule = saveRule(rule);
        }
        return rule;
    }
    @Override
    public Rule updateRule(final RuleDto ruleDto) {
        final Board board =  boardService.getBoardById(ruleDto.getIdBoard());
        if("time".equals(ruleDto.getType())){
                final  UsTimeRule usTimeRule = RulesMapper.INSTANCE.ruleDtoToTimeRule(ruleDto,board);
                if(usTimeRule==null){
                    return null;
                }
                usTimeRule.setId(UUID.fromString(ruleDto.getId()));
                return ruleRepository.save(usTimeRule);
            }
        if("stock".equals(ruleDto.getType())){
            final  StockRule stockRule = RulesMapper.INSTANCE.ruleDtoToStockRule(ruleDto);
            if(stockRule==null){
                return null;
            }
            stockRule.setId(UUID.fromString(ruleDto.getId()));
            return ruleRepository.save(stockRule);
        }
        return null;
    }

    @Override
    public void deleteRule(final RuleDto ruleDto) {
        ruleRepository.deleteById(UUID.fromString(ruleDto.getId()));
    }



    @Override
    public Rule getRuleByTypeAndId(final String type, final String id) {
        if(!"time".equals(type) && !"stock".equals(type)){
            return null;
        }
      return  ruleRepository.getRuleByTypeAndId(type,UUID.fromString(id));
    }

    @Override
    public Rule toggleActivateRule(final String id) {
        final Rule ruleToUpdate = ruleRepository.findById(UUID.fromString(id)).get();
        if(ruleToUpdate==null){
            return null;
        }
        else {
            ruleToUpdate.setActive(!(ruleToUpdate.isActive()));
            return ruleRepository.save(ruleToUpdate);
        }
    }

    @Override
    public List<UsTimeRule> findTimeRulesOfBoardByColumnNameAndType(final Board board, final String columnName, final String type) {
        return ruleRepository.getActiveTimeRulesByBoardAndTypeAndColumnName(board, type, columnName);
    }
}
