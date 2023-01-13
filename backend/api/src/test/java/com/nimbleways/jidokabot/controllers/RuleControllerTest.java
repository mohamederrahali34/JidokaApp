package com.nimbleways.jidokabot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbleways.jidokabot.contollers.RuleController;
import com.nimbleways.jidokabot.dto.RuleDto;
import com.nimbleways.jidokabot.entities.Rule;
import com.nimbleways.jidokabot.helpers.HttpMockMvcRequest;
import com.nimbleways.jidokabot.services.implementations.BoardServiceImpl;
import com.nimbleways.jidokabot.services.implementations.RuleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RuleController.class)
public class RuleControllerTest {

    @MockBean
    private RuleServiceImpl ruleService;
    @MockBean
    private BoardServiceImpl boardService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    final String uri = "/rules";

    @Test
    public void testaddRuleToBoard() throws Exception {
        String id = UUID.randomUUID().toString();
        RuleDto ruleDto = new RuleDto();
        Rule rule = new Rule();
        ArgumentCaptor<RuleDto> valueCapture = ArgumentCaptor.forClass(RuleDto.class);
        doReturn(rule).when(ruleService).addRuleToBoard(valueCapture.capture());
        MvcResult mvcResult = HttpMockMvcRequest.postMockMvcRequest(mockMvc, objectMapper, "/rules/new", ruleDto)
                .andExpect(status().isCreated()).andReturn();
        String actual = mvcResult.getResponse().getContentAsString();
        verify(ruleService, times(1)).addRuleToBoard(valueCapture.getValue());
        Assertions.assertEquals(objectMapper.valueToTree(rule), objectMapper.readTree(actual));
    }

    @Test
    public void testUpdateRule() throws Exception {
        RuleDto ruleDto = new RuleDto();
        Rule expected = new Rule();
        ArgumentCaptor<RuleDto> argumentCaptor = ArgumentCaptor.forClass(RuleDto.class);
        doReturn(expected).when(ruleService).updateRule(argumentCaptor.capture());
        MvcResult mvcResult = HttpMockMvcRequest
                .putMockMvcRequest(mockMvc, objectMapper, uri+"/update", ruleDto).andExpect(status().isOk())
                .andReturn();
        String actual = mvcResult.getResponse().getContentAsString();
        verify(ruleService, times(1)).updateRule(argumentCaptor.getValue());
        Assertions.assertEquals(objectMapper.valueToTree(expected), objectMapper.readTree(actual));
    }
    @Test
    public void testDeleteRule() throws Exception {
        RuleDto ruleDto = new RuleDto();
        ArgumentCaptor<RuleDto> argumentCaptor = ArgumentCaptor.forClass(RuleDto.class);
        doNothing().when(ruleService).deleteRule(ruleDto);
        HttpMockMvcRequest.deleteMockMvcRequest(mockMvc,objectMapper,uri+"/delete",ruleDto).andExpect(status().isOk());
    }
    @Test
    public void testGetRuleById() throws Exception {
        Rule rule = new Rule();
        String id = UUID.randomUUID().toString();
        doReturn(rule).when(ruleService).getRuleByTypeAndId("time",id);
        MvcResult mvcResult = HttpMockMvcRequest.getMockMvcRequest(mockMvc,uri+"/rule?type=time&id="+id).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        verify(ruleService,times(1)).getRuleByTypeAndId("time",id);
        Assertions.assertEquals(objectMapper.valueToTree(rule),objectMapper.readTree(response));
    }
    @Test
    public void testToggleActivateRule() throws Exception {
        final String id =  UUID.randomUUID().toString();
        final Rule rule = new Rule();
        doReturn(rule).when(ruleService).toggleActivateRule(id);
        MvcResult mvcResult = HttpMockMvcRequest.putMockMvcRequest(mockMvc,objectMapper,uri+"/activate?id="+id,null).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        verify(ruleService,times(1)).toggleActivateRule(id);
        Assertions.assertEquals(objectMapper.valueToTree(rule),objectMapper.readTree(response));

    }
}
