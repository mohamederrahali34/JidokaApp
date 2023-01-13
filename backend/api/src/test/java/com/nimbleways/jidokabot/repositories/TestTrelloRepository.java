package com.nimbleways.jidokabot.repositories;

import com.nimbleways.jidokabot.dto.trellodto.TrelloBoard;
import com.nimbleways.jidokabot.dto.trellodto.TrelloUser;
import com.nimbleways.jidokabot.repositories.implementations.TrelloRepository;
import com.nimbleways.jidokabot.utils.RestTemplateUtils;
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
public class TestTrelloRepository {

    @InjectMocks
    TrelloRepository projectManagementRepository;
    @Mock
    RestTemplateUtils restTemplateUtils;


    @Test
    public void testGetTrelloBoardById(){
        final String id = "624433f474c8b93ed0da02a5";
        final TrelloBoard board = new TrelloBoard();
        Mockito.doReturn(board).when(restTemplateUtils).get(Mockito.anyString(),Mockito.any());
        final TrelloBoard actual = projectManagementRepository.getTrelloBoardByIdAndToken(id,"token");
        Assertions.assertEquals(board.toString(),actual.toString());
    }
    @Test
    public void testGetConnectedUser(){
        final TrelloUser trelloUser = new TrelloUser();
        Mockito.doReturn(trelloUser).when(restTemplateUtils).get(Mockito.anyString(),Mockito.any());
        final TrelloUser actual = projectManagementRepository.getConnectedUserByToken("token");
        Assertions.assertEquals(trelloUser.toString(),actual.toString());
    }
    @Test
    public void testGetBoardsOfUser(){
        final List<?> boards = Arrays.asList(new TrelloBoard());
        final String id = UUID.randomUUID().toString();
        Mockito.doReturn(boards).when(restTemplateUtils).get(Mockito.anyString(),Mockito.any());
        final List<?> actual = projectManagementRepository.getBoardsOfUserByToken(id,"email" );
        Assertions.assertEquals(1,actual.size());
    }
}
