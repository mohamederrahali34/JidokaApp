package com.nimbleways.jidokabot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbleways.jidokabot.contollers.BoardController;
import com.nimbleways.jidokabot.dto.BoardDto;
import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.entities.Rule;
import com.nimbleways.jidokabot.entities.Webhook;
import com.nimbleways.jidokabot.fixtures.BoardDtoFixtureHelper;
import com.nimbleways.jidokabot.fixtures.BoardFixtureHelper;
import com.nimbleways.jidokabot.helpers.HttpMockMvcRequest;
import com.nimbleways.jidokabot.services.implementations.BoardServiceImpl;
import com.nimbleways.jidokabot.services.implementations.RuleServiceImpl;
import com.nimbleways.jidokabot.services.implementations.WebhookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BoardController.class)
public class BoardControllerTest {

    @MockBean
    private BoardServiceImpl boardService;
    @MockBean
    private RuleServiceImpl ruleService;
    @MockBean
    private WebhookServiceImpl webhookService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateBoard() throws Exception {
        // GIVEN
        BoardDto boardDto = new BoardDto();
        UUID uuid = UUID.randomUUID();
        boardDto.setId(uuid.toString());
        Board board = new Board();
        board.setId(uuid.toString());
        Webhook expected = new Webhook() ;
        final String userId = UUID.randomUUID().toString();
        // WHEN
        when(boardService.save(boardDto.getId(),userId )).thenReturn(board);
        doReturn(expected).when(webhookService).createWebHook(any(String.class),any(String.class) );
        MvcResult mvcResult = HttpMockMvcRequest.postMockMvcRequest(mockMvc, objectMapper, "/boards/new?userId="+userId, boardDto)
                .andExpect(status().isCreated()).andReturn();
        // then
        String actual = mvcResult.getResponse().getContentAsString();
        verify(boardService, Mockito.times(1)).save(boardDto.getId(),userId );
        verify(webhookService).createWebHook(boardDto.getId(), userId);
        Assertions.assertEquals(objectMapper.valueToTree(board), objectMapper.readTree(actual));
    }

    @Test
    void testGetRulesOfBoard() throws Exception {
        // GIVEN
        BoardDto boardDto = BoardDtoFixtureHelper.aBoardDto().generate();
        List<Rule> expected = new ArrayList<>();
        // WHEN
        Board board = BoardFixtureHelper.aBoard().generate();
        when(boardService.getBoardById(boardDto.getId())).thenReturn(board);
        when(ruleService.getRulesByBoard(board)).thenReturn(expected);
        // THEN
        MvcResult mvcResult = HttpMockMvcRequest.postMockMvcRequest(mockMvc, objectMapper, "/boards/rules", boardDto)
                .andExpect(status().isOk()).andReturn();
        String actual = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.valueToTree(expected), objectMapper.readTree(actual));
    }

}
