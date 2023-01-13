package com.nimbleways.jidokabot.contollers;

import com.nimbleways.jidokabot.dto.trellodto.TrelloBoard;
import com.nimbleways.jidokabot.services.implementations.TrelloServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/trello")
public class TrelloController {

    private final TrelloServiceImpl trelloService;

    public TrelloController(final TrelloServiceImpl trelloService) {
        this.trelloService = trelloService;
    }

    @GetMapping("/board/{id}")
    public TrelloBoard getBoard(@PathVariable @Valid final String id, @RequestParam @Valid final String userId)
            throws IOException {
        return trelloService.getTrelloBoardById(id, userId);
    }

    @GetMapping("/members/{id}/boards")
    public List<?> getBoardsOfUser(@PathVariable @Valid final String id, @RequestParam @Valid final String userId) {
        return trelloService.getBoardsOfUser(id, userId);
    }

    @GetMapping("/boards/{id}/lists")
    public List<?> getColumnsOfBoard(@PathVariable @Valid final String id, @RequestParam final String userId) {
        return trelloService.getColumnsOfBoard(id, userId);
    }
}
