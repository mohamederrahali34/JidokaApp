package com.nimbleways.jidokabot.repositories;

import com.nimbleways.jidokabot.dto.trellodto.TrelloBoard;
import com.nimbleways.jidokabot.dto.trellodto.TrelloUser;

import java.util.List;

public interface IProjectManagementRepository {
    TrelloBoard getTrelloBoardByIdAndToken(String id, String token);
    TrelloUser getConnectedUserByToken(String token);
    List<?> getBoardsOfUserByToken(String id, String token);
    List<?> getColumnsOfBoardByToken(String id, String token);
    List<?> getCardsOfTrelloListByToken(String id, String token);
}
