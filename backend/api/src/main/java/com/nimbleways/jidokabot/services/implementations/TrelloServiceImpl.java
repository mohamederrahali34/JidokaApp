package com.nimbleways.jidokabot.services.implementations;

import com.nimbleways.jidokabot.dto.trellodto.TrelloBoard;
import com.nimbleways.jidokabot.dto.trellodto.TrelloUser;
import com.nimbleways.jidokabot.entities.User;
import com.nimbleways.jidokabot.repositories.IProjectManagementRepository;
import com.nimbleways.jidokabot.repositories.UserRepository;
import com.nimbleways.jidokabot.services.IProjectManagementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrelloServiceImpl implements IProjectManagementService {
    private final IProjectManagementRepository projectManagementRepository;
    private final UserRepository userRepository;

    @Override
    public TrelloBoard getTrelloBoardById(final String id,final  String userId) {
        final User user = userRepository.getById(userId);
       return  projectManagementRepository.getTrelloBoardByIdAndToken(id,user.getToken() );
    }

    @Override
    public TrelloUser getConnectedUser(final String token) {
        return projectManagementRepository.getConnectedUserByToken(token);
    }

    @Override
    public List<?> getBoardsOfUser(final String id,final  String userId) {
        final User user = userRepository.getById(userId);
        return projectManagementRepository.getBoardsOfUserByToken(id,user.getToken() );
    }

    @Override
    public List<?> getColumnsOfBoard(final String id,final  String userId) {
        final User user = userRepository.getById(userId);
        return projectManagementRepository.getColumnsOfBoardByToken(id, user.getToken());
    }

    @Override
    public List<?> getCardsOfTrelloList(final String id,final  String userId) {
        final User user = userRepository.getById(userId);
        return projectManagementRepository.getCardsOfTrelloListByToken(id, user.getToken());
    }
}
