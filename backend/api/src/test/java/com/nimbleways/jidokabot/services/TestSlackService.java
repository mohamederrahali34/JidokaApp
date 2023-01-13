package com.nimbleways.jidokabot.services;

import com.nimbleways.jidokabot.entities.Channel;
import com.nimbleways.jidokabot.repositories.IMessageRepository;
import com.nimbleways.jidokabot.services.implementations.SlackMessageService;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith({SpringExtension.class})
public class TestSlackService {

    @InjectMocks
    private  SlackMessageService slackService;

   @Mock
   private IMessageRepository messageRepository;

    @Test
    public void testGetAllChannelService() throws SlackApiException, IOException {
        //GIVEN
        List<Channel> channels = Arrays.asList(new Channel("jidokabotchannel"));
        Mockito.doReturn(channels).when(messageRepository).getAllChannels();
        List<Channel> actual =slackService.getAllChannels();
        Set<Channel> set =  actual.stream().filter(c->c.getName().equalsIgnoreCase("jidokabotchannel")).collect(Collectors.toSet());
        Assertions.assertThat(set.isEmpty()).isFalse();
    }
    @Test
    public void testSendMessageToSlackChannel() throws SlackApiException, IOException {
        ChatPostMessageResponse expected  = new ChatPostMessageResponse();
        Mockito.doReturn(expected).when(messageRepository).sendSlackMessage("testing","#jidokabotchannel");
        ChatPostMessageResponse chatPostMessageResponse =  slackService.sendSlackMessage("testing","#jidokabotchannel");
        Assertions.assertThat(chatPostMessageResponse).isNotNull();
        Assertions.assertThat(chatPostMessageResponse.getError()).isNull();
    }

}
