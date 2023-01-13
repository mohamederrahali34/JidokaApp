package com.nimbleways.jidokabot.helpers;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class HttpMockMvcRequest {



    public static ResultActions postMockMvcRequest(MockMvc mockMvc, ObjectMapper objectMapper, String uri, Object obj)
            throws Exception {

        return mockMvc.perform(post(uri)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(obj)));

    }

    public static ResultActions getMockMvcRequest(MockMvc mockMvc, String path)
            throws Exception {

        return mockMvc.perform(get(path)
                .contentType("application/json"));

    }
    public static ResultActions putMockMvcRequest(MockMvc mockMvc,ObjectMapper objectMapper,String uri,Object obj) throws Exception  {
        return mockMvc.perform(put(uri).contentType("application/json").content(objectMapper.writeValueAsString(obj)));
    }
    public static ResultActions deleteMockMvcRequest(MockMvc mockMvc,ObjectMapper objectMapper,String uri,Object obj) throws Exception  {
        return mockMvc.perform(delete(uri).contentType("application/json").content(objectMapper.writeValueAsString(obj)));
    }
}
