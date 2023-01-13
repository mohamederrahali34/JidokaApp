package com.nimbleways.jidokabot.services;

import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.entities.UsTimeRule;
import com.nimbleways.jidokabot.entities.User;
import com.nimbleways.jidokabot.fixtures.BoardFixtureHelper;
import com.nimbleways.jidokabot.repositories.BoardRepository;
import com.nimbleways.jidokabot.repositories.RuleRepository;
import com.nimbleways.jidokabot.repositories.UserRepository;
import com.nimbleways.jidokabot.services.implementations.BoardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.nimbleways.jidokabot.fixtures.BoardFixtureHelper.aBoard;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith({SpringExtension.class})
public class TestBoardServiceImpl {

    @InjectMocks
    BoardServiceImpl boardService ;

    @Mock
    BoardRepository boardRepository ;
    @Mock
    UserRepository userRepository;
    @Mock
    RuleRepository ruleRepository ;



    @Test
    public void testGetBoardById(){
        String idBoard = UUID.randomUUID().toString();
        Board board = BoardFixtureHelper.aBoard().generate() ;
        //when
        Mockito.when(boardRepository.findById(idBoard)).thenReturn(Optional.of(board)) ;
        //Then
        Board actual = boardService.getBoardById(idBoard) ;
        assertEquals(board.toString(),actual.toString());
    }

    @Test
    public void testSaveBoard(){
        //GIVEN
        String boardId = UUID.randomUUID().toString() ;
        String userId = UUID.randomUUID().toString();
        Board board = new Board();
        board.setId(boardId);
        User user = new User();
        doReturn(user).when(userRepository).getById(any(String.class));
        doReturn(board).when(boardRepository).save(any(Board.class));
        Board actual = boardService.save(boardId,userId ) ;
        assertEquals(board,actual);
    }
    @Test
    public void testFindTimeRulesOfBoard(){
        List<UsTimeRule> usTimeRules = Arrays.asList(new UsTimeRule(),new UsTimeRule()) ;
        Board board = aBoard().generate() ;
        doReturn(usTimeRules).when(ruleRepository).getAllTimeRulesByBoard(board);
        List<UsTimeRule> actual = boardService.findTimeRulesOfBoard(board) ;
        assertEquals(usTimeRules,actual);
    }


}
