package com.nimbleways.jidokabot.services;

import com.nimbleways.jidokabot.dto.trellodto.TrelloBoard;
import com.nimbleways.jidokabot.dto.trellodto.TrelloUser;
import com.nimbleways.jidokabot.entities.User;
import com.nimbleways.jidokabot.repositories.IProjectManagementRepository;
import com.nimbleways.jidokabot.repositories.UserRepository;
import com.nimbleways.jidokabot.services.implementations.TrelloServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith({SpringExtension.class})

public class TestTrelloServiceImpl {

    @InjectMocks
    private TrelloServiceImpl trelloService;

    @Mock
    UserRepository userRepository;
    @Mock
    private IProjectManagementRepository projectManagementRepository;

    @Test
    public void testGetTrelloBoardById()  {
        final String id = UUID.randomUUID().toString();
        final TrelloBoard board = new TrelloBoard();
        final String userId = UUID.randomUUID().toString();
        final User user = new User();
        user.setToken("token");
        Mockito.doReturn(user).when(userRepository).getById(userId);
        Mockito.doReturn(board).when(projectManagementRepository).getTrelloBoardByIdAndToken(Mockito.anyString(),Mockito.anyString());
        final TrelloBoard actual = trelloService.getTrelloBoardById(id, userId);
        Assertions.assertEquals(board.toString(),actual.toString());
    }
    @Test
    public void testGetConnectedUser(){
        final TrelloUser trelloUser = new TrelloUser();
        final String userId = UUID.randomUUID().toString();
        final User user = new User();
        user.setToken("token");
        Mockito.doReturn(user).when(userRepository).getById(userId);
        Mockito.doReturn(trelloUser).when(projectManagementRepository).getConnectedUserByToken(Mockito.anyString());
        final TrelloUser actual  = trelloService.getConnectedUser(userId);
        Assertions.assertEquals(trelloUser.toString(),actual.toString());
    }
    @Test
    public void testGetBoardsOfUser(){
        final String id = UUID.randomUUID().toString();
        List<?> boards = Arrays.asList(new TrelloBoard());
        final String userId = UUID.randomUUID().toString();
        final User user = new User();
        user.setToken("token");
        Mockito.doReturn(user).when(userRepository).getById(userId);
        Mockito.doReturn(boards).when(projectManagementRepository).getBoardsOfUserByToken(id,user.getToken() );
        final List<?> actual = trelloService.getBoardsOfUser(id,userId );
        Assertions.assertEquals(actual.size(),1);
    }
}
