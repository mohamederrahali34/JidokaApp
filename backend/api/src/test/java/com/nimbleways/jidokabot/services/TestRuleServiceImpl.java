package com.nimbleways.jidokabot.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbleways.jidokabot.dto.RuleDto;
import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.entities.Rule;
import com.nimbleways.jidokabot.entities.StockRule;
import com.nimbleways.jidokabot.entities.UsTimeRule;
import com.nimbleways.jidokabot.mappers.RulesMapper;
import com.nimbleways.jidokabot.repositories.BoardRepository;
import com.nimbleways.jidokabot.repositories.RuleRepository;
import com.nimbleways.jidokabot.services.implementations.BoardServiceImpl;
import com.nimbleways.jidokabot.services.implementations.RuleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith({SpringExtension.class})

public class TestRuleServiceImpl {

    @InjectMocks
    RuleServiceImpl ruleService ;

    @Mock
    BoardServiceImpl boardService ;
    @Mock
    BoardRepository boardRepository;
    @Mock
    RuleRepository ruleRepository ;
    ObjectMapper objectMapper  = new ObjectMapper();
    @Test
    public void testAddTimeRuleToBoard(){
        //GIVEN
        RuleDto ruleDto = new RuleDto();
        ruleDto.setType("time");
        ruleDto.setParams(Map.of("duration","12","columnName","doing","unit","h","name","name"));
        Board board = new Board() ;
        board.setId(UUID.randomUUID().toString());
        Rule rule = RulesMapper.INSTANCE.ruleDtoToTimeRule(ruleDto,board);
        List<UsTimeRule> rules = Arrays.asList(new UsTimeRule(),new UsTimeRule()) ;
        //WHEN
        doReturn(board).when(boardService).getBoardById(any(String.class));
        doReturn(rule).when(ruleRepository).save(any(Rule.class)) ;
        doReturn(rules).when(ruleRepository).getTimeRulesByBoardAndColumnName(any(Board.class),any(String.class)) ;
        Rule actual = ruleService.addRuleToBoard(ruleDto) ;
        //THEN
        assertEquals(rule,actual) ;
    }
    @Test
    public void testAddStockRuleToBoard(){
        //GIVEN
        RuleDto ruleDto = new RuleDto();
        ruleDto.setType("stock");
        ruleDto.setParams(Map.of("stock","4","columnName","doing","owner","anyone"));
        Board board = new Board() ;
        board.setId(UUID.randomUUID().toString());
        Rule rule = RulesMapper.INSTANCE.ruleDtoToStockRule(ruleDto);
        List<StockRule> rules = Arrays.asList(new StockRule(),new StockRule()) ;
        //WHEN
        doReturn(board).when(boardService).getBoardById(any(String.class));
        doReturn(rule).when(ruleRepository).save(any(Rule.class)) ;
        doReturn(rules).when(ruleRepository).getStockRulesByBoardAndColumnName(any(Board.class),any(String.class)) ;
        Rule actual = ruleService.addRuleToBoard(ruleDto) ;
        //THEN
        assertEquals(rule,actual) ;
    }
    @Test
    public void testGetRulesByBoard() throws JsonProcessingException {
        //GIVEN
        Board board = new Board();
        List<Rule> expected = Arrays.asList(new Rule());
        //WHEN
        doReturn(expected).when(ruleRepository).findByBoard(board);
        //THEN
        List<Rule> actual = ruleService.getRulesByBoard(board);
        assertEquals(objectMapper.writeValueAsString(expected),objectMapper.writeValueAsString(actual));
    }

    @Test
    public void testfindTimeRulesOfBoardByColumnNameAndType() throws JsonProcessingException {
        //GIVEN
        Board board = new Board();
        String columnName = "doing";
        String type = "time";
        List<UsTimeRule> expected = Arrays.asList(new UsTimeRule());
        //WHEN
        doReturn(expected).when(ruleRepository).getActiveTimeRulesByBoardAndTypeAndColumnName(board,type,columnName);
        //THEN
        List<UsTimeRule> actual = ruleService.findTimeRulesOfBoardByColumnNameAndType(board,columnName,type);
        assertEquals(objectMapper.writeValueAsString(expected),objectMapper.writeValueAsString(actual));
    }
    @Test
    public void testUpdateTimeRule(){
        //GIVEN
        RuleDto ruleDto = new RuleDto();
        ruleDto.setId(UUID.randomUUID().toString());
        ruleDto.setActive(false);
        ruleDto.setType("time");
        final Map<String,String> params = new HashMap<>();
        params.put("duration","5");
        params.put("unit","m");
        params.put("columnName","Doing");
        ruleDto.setMessage("new message");
        ruleDto.setActive(true);
        ruleDto.setParams(params);
        ruleDto.setIdBoard(UUID.randomUUID().toString());
        Board board = new Board();
        final UsTimeRule expected = new UsTimeRule();
        expected.setMessage("new message");
        doReturn(Optional.of(board)).when(boardRepository).findById(any(String.class));
        doReturn(expected).when(ruleRepository).save(any(Rule.class));
        final Rule  actual = ruleService.updateRule(ruleDto);
        Assertions.assertEquals(expected.getMessage(),actual.getMessage());
    }
    @Test
    public void testUpdateStockRule(){
        //GIVEN
        RuleDto ruleDto = new RuleDto();
        ruleDto.setId(UUID.randomUUID().toString());
        ruleDto.setActive(false);
        ruleDto.setType("stock");
        final Map<String,String> params = new HashMap<>();
        params.put("owner","anyone");
        params.put("stock","5");
        params.put("columnName","Doing");
        ruleDto.setMessage("new message");
        ruleDto.setActive(true);
        ruleDto.setParams(params);
        ruleDto.setIdBoard(UUID.randomUUID().toString());
        Board board = new Board();
        final StockRule expected = new StockRule();
        expected.setMessage("new message");
        doReturn(Optional.of(board)).when(boardRepository).findById(any(String.class));
        doReturn(expected).when(ruleRepository).save(any(Rule.class));
        final Rule  actual = ruleService.updateRule(ruleDto);
        Assertions.assertEquals(expected.getMessage(),actual.getMessage());
    }
    @Test
    void testUpdateStockRuleShouldReturnNullIFDtoIsInvalid(){
        //GIVEN
        //DTO invalid param missing stock param
        RuleDto ruleDto = new RuleDto();
        ruleDto.setId(UUID.randomUUID().toString());
        ruleDto.setActive(false);
        ruleDto.setType("stock");
        final Map<String,String> params = new HashMap<>();
        params.put("owner","anyone");
        params.put("columnName","Doing");
        ruleDto.setMessage("new message");
        ruleDto.setActive(true);
        ruleDto.setParams(params);
        ruleDto.setIdBoard(UUID.randomUUID().toString());
        Board board = new Board();
        final StockRule expected = new StockRule();
        expected.setMessage("new message");
        doReturn(Optional.of(board)).when(boardRepository).findById(any(String.class));
        final Rule  actual = ruleService.updateRule(ruleDto);
        Assertions.assertTrue(actual==null);

    }
    @Test
    void testUpdateTimeRuleShouldReturnNullIFDtoIsInvalid(){
        //GIVEN
        //DTO invalid param missing stock param
        RuleDto ruleDto = new RuleDto();
        ruleDto.setId(UUID.randomUUID().toString());
        ruleDto.setActive(false);
        ruleDto.setType("time");
        final Map<String,String> params = new HashMap<>();
        params.put("owner","anyone");
        params.put("columnName","Doing");
        ruleDto.setMessage("new message");
        ruleDto.setActive(true);
        ruleDto.setParams(params);
        ruleDto.setIdBoard(UUID.randomUUID().toString());
        Board board = new Board();
        final UsTimeRule expected = new UsTimeRule();
        expected.setMessage("new message");
        doReturn(Optional.of(board)).when(boardRepository).findById(any(String.class));
        final Rule  actual = ruleService.updateRule(ruleDto);
        Assertions.assertTrue(actual==null);

    }
    @Test
    public void testToggleActivateRule(){
        //GIVEN
        final String id = UUID.randomUUID().toString();
        final Rule oldRule = new Rule();
        oldRule.setActive(false);
        doReturn(Optional.of(oldRule)).when(ruleRepository).findById(UUID.fromString(id));
        final Rule newRule = new Rule();
        newRule.setActive(true);
        doReturn(newRule).when(ruleRepository).save(oldRule);
        final Rule actual = ruleService.toggleActivateRule(id);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(true,actual.isActive());
    }
    @Test
    public void testGetTimeRuleByTypeAndId() throws JsonProcessingException {
        UsTimeRule rule = new UsTimeRule();
        String id  = UUID.randomUUID().toString();
        doReturn(rule).when(ruleRepository).getRuleByTypeAndId("time",UUID.fromString(id));
        Rule actual = ruleService.getRuleByTypeAndId("time", id);
        assertEquals(objectMapper.writeValueAsString(rule),objectMapper.writeValueAsString(actual));
    }
    @Test
    public void testGetStockRuleByTypeAndId() throws JsonProcessingException {
        StockRule rule = new StockRule();
        String id  = UUID.randomUUID().toString();
        doReturn(rule).when(ruleRepository).getRuleByTypeAndId("stock",UUID.fromString(id));
        Rule actual = ruleService.getRuleByTypeAndId("stock", id);
        assertEquals(objectMapper.writeValueAsString(rule),objectMapper.writeValueAsString(actual));
    }
    @Test
    public void testGetRuleByTypeAndIdIfTypeIsInvalid() throws JsonProcessingException {
        Rule actual = ruleService.getRuleByTypeAndId("otherType", UUID.randomUUID().toString());
        Assertions.assertTrue(actual==null);
    }
}
