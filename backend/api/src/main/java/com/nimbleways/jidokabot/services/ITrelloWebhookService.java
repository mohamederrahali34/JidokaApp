package com.nimbleways.jidokabot.services;

import com.nimbleways.jidokabot.dto.useractiondto.UserActionDetailsDTO;
import com.nimbleways.jidokabot.entities.Webhook;
import com.slack.api.methods.SlackApiException;

import java.io.IOException;

public interface ITrelloWebhookService {
    void handleUserAction(UserActionDetailsDTO userActionDetails) throws SlackApiException, IOException;

    Webhook createWebHook(String idBoard, String userId);
}
