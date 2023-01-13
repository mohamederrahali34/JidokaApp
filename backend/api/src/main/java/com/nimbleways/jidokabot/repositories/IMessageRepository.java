package com.nimbleways.jidokabot.repositories;

import com.nimbleways.jidokabot.entities.Channel;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import java.io.IOException;
import java.util.List;

public interface IMessageRepository {
    ChatPostMessageResponse sendSlackMessage(String message, String channel) throws SlackApiException, IOException;
    List<Channel> getAllChannels() throws SlackApiException, IOException;
}
