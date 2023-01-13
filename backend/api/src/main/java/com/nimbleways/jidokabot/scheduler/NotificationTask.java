package com.nimbleways.jidokabot.scheduler;

import com.nimbleways.jidokabot.services.IMessageService;
import com.slack.api.methods.SlackApiException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Data
@Getter
@Setter
@Component
public class NotificationTask implements Runnable {
    private String message;
    private String channel;
    private String cardId;
    private final IMessageService slackService;
    private final TimeTasksManager timeTasksManager;


    public NotificationTask(final IMessageService slackService, final TimeTasksManager timeTasksManager) {
        this.slackService = slackService;
        this.timeTasksManager = timeTasksManager;
    }

    @Override
    public void run() {
        try {
            slackService.sendSlackMessage(message,channel);
            timeTasksManager.cancelTimeTask(cardId);
        } catch (SlackApiException  | IOException e) {
        }
    }
}
