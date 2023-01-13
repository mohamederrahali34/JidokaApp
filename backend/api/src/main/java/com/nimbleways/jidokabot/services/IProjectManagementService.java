package com.nimbleways.jidokabot.services;

import com.nimbleways.jidokabot.dto.trellodto.TrelloBoard;
import com.nimbleways.jidokabot.dto.trellodto.TrelloUser;

import java.util.List;

public interface IProjectManagementService {
    TrelloBoard getTrelloBoardById(String id, String userId);
    TrelloUser getConnectedUser(String token);
    List<?> getBoardsOfUser(String id, String userId);
    List<?> getColumnsOfBoard(String id, String userId);
    List<?> getCardsOfTrelloList(String id, String userId);
}
