package com.nimbleways.jidokabot.contollers;

import com.nimbleways.jidokabot.entities.Channel;
import com.nimbleways.jidokabot.services.IMessageService;
import com.slack.api.methods.SlackApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController()
@RequestMapping("/slack")
public class ChannelController {

    final private IMessageService slackService;

    public ChannelController(final IMessageService slackService) {
        this.slackService = slackService;
    }

    @GetMapping("/channels")
    public ResponseEntity<List<Channel>> getAllChannels() throws SlackApiException, IOException {
       return  ResponseEntity.status(HttpStatus.OK).body(slackService.getAllChannels());
    }
}
