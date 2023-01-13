package com.nimbleways.jidokabot.utils;

import com.nimbleways.jidokabot.utils.messageformatters.IMessageFormatter;
import com.nimbleways.jidokabot.utils.messageformatters.MessageFormater;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
@ExtendWith({SpringExtension.class})
@Import({MessageFormater.class})
public class TestMessageFormatter {

    @Autowired
    IMessageFormatter messageFormater;
    @Test
    public void testMessageFormatter(){
        String message = "this a {param}";
        Map<String,String> params = new HashMap<>();
        params.put("param","paramValue");
        String result = messageFormater.formatMessage(message,params);
        Assertions.assertEquals("this a paramValue",result);
    }
}
