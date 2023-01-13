package com.nimbleways.jidokabot.repositories;

import com.nimbleways.jidokabot.repositories.implementations.SlackMessageRepository;
import com.slack.api.methods.SlackApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith({SpringExtension.class})
@Import({ SlackMessageRepository.class })
public class TestMessageSlackRepository {

    @Autowired
    private IMessageRepository messageRepository;



    @Test
    public void testSendMessageToSlackChannel() throws SlackApiException, IOException {
        final String message = "test send message";
        final String channel = "projet";
        Assertions.assertDoesNotThrow(()->messageRepository.sendSlackMessage(message,channel));
    }

    @Test
    public void testGetChannelOfWorkspace() throws SlackApiException, IOException {
        Assertions.assertDoesNotThrow(()-> {
            Assertions.assertTrue(messageRepository.getAllChannels().size()>0);
        });
    }
}
