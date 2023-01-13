package com.nimbleways.jidokabot.repositories.implementations;

import com.nimbleways.jidokabot.entities.Channel;
import com.nimbleways.jidokabot.repositories.IMessageRepository;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.request.conversations.ConversationsListRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SlackMessageRepository implements IMessageRepository {

    private final MethodsClient methodsClient;

    public SlackMessageRepository() {
        final Slack slack = Slack.getInstance();
        final String token =  "xoxb-3394601740902-3394606570998-vOjNtowFFQ9SJRevjyEQAYev";// System.getenv("SLACK_TOKEN");
        this.methodsClient = slack.methods(token);
    }


    @Override
    public ChatPostMessageResponse sendSlackMessage(final String message,final String channel) throws SlackApiException, IOException {
        final ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel) // Use a channel ID `C1234567` is preferrable
                .text(message)
                .build();
        return this.methodsClient.chatPostMessage(request);
    }

    @Override
    public List<Channel> getAllChannels() throws SlackApiException, IOException {
        final ConversationsListRequest conversationsListRequest = ConversationsListRequest.builder().build();
        final List<Channel> channels= new ArrayList<>();
        this.methodsClient.conversationsList(conversationsListRequest).getChannels().forEach(channel->{
            channels.add(new Channel(channel.getName()));
        });
        return channels;
    }

}
