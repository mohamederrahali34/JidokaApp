package com.nimbleways.jidokabot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbleways.jidokabot.contollers.WebhookController;
import com.nimbleways.jidokabot.dto.useractiondto.UserActionDetailsDTO;
import com.nimbleways.jidokabot.helpers.HttpMockMvcRequest;
import com.nimbleways.jidokabot.services.implementations.WebhookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WebhookController.class)
public class WebhookControllerTest {

    @MockBean
    WebhookServiceImpl trelloWebhookService ;
    @Autowired
    private MockMvc mockMvc ;

    @Autowired
    private ObjectMapper objectMapper ;
    final String uri = "/webhooks" ;
    @Test
    public void testConsumeWebhook() throws Exception {
        //GIVEN
        UserActionDetailsDTO userActionDetails = new UserActionDetailsDTO() ;
        //WHEN
        ArgumentCaptor<UserActionDetailsDTO> valueCapture = ArgumentCaptor.forClass(UserActionDetailsDTO.class);
        doNothing().when(trelloWebhookService).handleUserAction(valueCapture.capture());
         HttpMockMvcRequest.postMockMvcRequest(mockMvc,objectMapper,uri+"/new", userActionDetails)
                .andExpect(status().isCreated());
         //THEN
        verify(trelloWebhookService,times(1)).handleUserAction(valueCapture.getValue()) ;
    }
}