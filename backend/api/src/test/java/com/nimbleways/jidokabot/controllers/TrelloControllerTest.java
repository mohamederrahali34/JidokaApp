package com.nimbleways.jidokabot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbleways.jidokabot.contollers.TrelloController;
import com.nimbleways.jidokabot.dto.trellodto.TrelloBoard;
import com.nimbleways.jidokabot.dto.trellodto.TrelloUser;
import com.nimbleways.jidokabot.helpers.HttpMockMvcRequest;
import com.nimbleways.jidokabot.services.implementations.TrelloServiceImpl;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = TrelloController.class)
public class TrelloControllerTest {
    @MockBean
    private TrelloServiceImpl trelloService;
    @Autowired
    private MockMvc mockMvc;
    private final String uri = "/trello";

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testGetBoardById() throws Exception {
        //Given
        final String id = UUID.randomUUID().toString();
        final TrelloBoard board = new TrelloBoard();
        final String userId = UUID.randomUUID().toString();
        //WHEN
        Mockito.doReturn(board).when(trelloService).getTrelloBoardById(id,userId );
        MvcResult mvcResult = HttpMockMvcRequest.getMockMvcRequest(mockMvc,uri+"/board/"+id+"?userId="+userId).andExpect(status().isOk()).andReturn();
        String response  = mvcResult.getResponse().getContentAsString();
        //then
        Assertions.assertEquals(objectMapper.valueToTree(board),objectMapper.readTree(response));
    }

    @Test
    public void testGetBoardsOfUser() throws Exception {
        //GIVEN
        final List<?> list = new ArrayList<>();
        final String id = UUID.randomUUID().toString();
        final String userId = UUID.randomUUID().toString();

        Mockito.doReturn(list).when(trelloService).getBoardsOfUser(id, userId);
        final MvcResult mvcResult = HttpMockMvcRequest.getMockMvcRequest(mockMvc,uri+"/members/"+id+"/boards"+"?userId="+userId).andExpect(status().isOk()).andReturn();
        final String response  = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.valueToTree(list),objectMapper.readTree(response));
    }
}
