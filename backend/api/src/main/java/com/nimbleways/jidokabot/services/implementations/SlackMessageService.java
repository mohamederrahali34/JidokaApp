package com.nimbleways.jidokabot.services.implementations;

import com.nimbleways.jidokabot.entities.Channel;
import com.nimbleways.jidokabot.repositories.IMessageRepository;
import com.nimbleways.jidokabot.services.IMessageService;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SlackMessageService implements IMessageService {

    private final IMessageRepository messageRepository;

    @Autowired
    public SlackMessageService(final IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @Override
    public ChatPostMessageResponse sendSlackMessage(final String message,final String channel) throws SlackApiException, IOException {
        return messageRepository.sendSlackMessage(message,channel);
    }

    @Override
    public List<Channel> getAllChannels() throws SlackApiException, IOException {
       return messageRepository.getAllChannels();
    }
}
