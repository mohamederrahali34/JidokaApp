package com.nimbleways.jidokabot.contollers;


import com.nimbleways.jidokabot.dto.useractiondto.UserActionDetailsDTO;
import com.nimbleways.jidokabot.services.ITrelloWebhookService;
import com.slack.api.methods.SlackApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/webhooks")
public class WebhookController {

    private final ITrelloWebhookService trelloWebhookService;

    public WebhookController(final ITrelloWebhookService trelloWebhookService) {
        this.trelloWebhookService = trelloWebhookService;
    }

    @GetMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public void verifyCreatingwebhook() {
    }



    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void consumeWebhook(@RequestBody @Valid final UserActionDetailsDTO userActionDetails) throws SlackApiException, IOException {
        trelloWebhookService.handleUserAction(userActionDetails);
    }

}
