package com.nimbleways.jidokabot.mappers;

import com.nimbleways.jidokabot.dto.BoardDto;
import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.fixtures.BoardDtoFixtureHelper;
import com.nimbleways.jidokabot.fixtures.BoardFixtureHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class BoardMapperTest {

    @Test
    public void testBoardDtoToBoardMapper(){
        UUID uuid = UUID.randomUUID() ;
        BoardDto boardDto = BoardDtoFixtureHelper.aBoardDto().generate() ;
        boardDto.setId(uuid.toString());
        Board actual = BoardFixtureHelper.aBoard().generate() ;
        BoardMapper.INSTANCE.boardDtoToBoard(boardDto,actual);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(uuid.toString(),actual.getId());
    }
}
