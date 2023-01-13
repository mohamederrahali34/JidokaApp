package com.nimbleways.jidokabot.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbleways.jidokabot.contollers.ChannelController;
import com.nimbleways.jidokabot.entities.Channel;
import com.nimbleways.jidokabot.helpers.HttpMockMvcRequest;
import com.nimbleways.jidokabot.services.implementations.SlackMessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ChannelController.class)
public class ChannelControllerTest {
  @MockBean
  SlackMessageService slackService;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    
    private String uri = "/slack/";

    @Test
    public void testGetAllChannels() throws Exception {
        List<Channel> channels = Arrays.asList(new Channel("jidokabotchannel"));
        //WHEN
        Mockito.doReturn(channels).when(slackService).getAllChannels();
        MvcResult result =  HttpMockMvcRequest.getMockMvcRequest(mockMvc,uri+"channels").andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.valueToTree(channels),objectMapper.readTree(response));
    }


}
