package com.nimbleways.jidokabot.services;

import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.entities.UsTimeRule;

import java.util.List;

public interface IBoardService {
    Board getBoardById(String idBoard);

    Board save(String boardDtoId, String userId);

    List<UsTimeRule> findTimeRulesOfBoard(Board board);
}
