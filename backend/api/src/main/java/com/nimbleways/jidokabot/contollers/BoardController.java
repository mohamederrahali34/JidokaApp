package com.nimbleways.jidokabot.contollers;


import com.nimbleways.jidokabot.dto.BoardDto;
import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.entities.Rule;
import com.nimbleways.jidokabot.services.IBoardService;
import com.nimbleways.jidokabot.services.IRuleService;
import com.nimbleways.jidokabot.services.ITrelloWebhookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final IBoardService boardService;
    private final IRuleService ruleService;
    private final ITrelloWebhookService trelloWebhookService;

    public BoardController(final IBoardService boardService, final IRuleService ruleService, final ITrelloWebhookService trelloWebhookService) {
        this.boardService = boardService;
        this.ruleService = ruleService;
        this.trelloWebhookService = trelloWebhookService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Board> createBoard(@RequestBody @Valid final BoardDto boardDto,@RequestParam @Valid final String userId) {
        final Board savedBoard = boardService.save(boardDto.getId(),userId );
        trelloWebhookService.createWebHook(boardDto.getId(),userId );
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBoard);
    }

    @PostMapping("/rules")
    public ResponseEntity<List<Rule>> getRulesOfBoard(@RequestBody @Valid final BoardDto boardDto) {
        final Board board = boardService.getBoardById(boardDto.getId());
        final List<Rule> rules = ruleService.getRulesByBoard(board);
        return ResponseEntity.status(HttpStatus.OK).body(rules);
    }
}
