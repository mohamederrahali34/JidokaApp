package com.nimbleways.jidokabot.mappers;

import com.nimbleways.jidokabot.dto.BoardDto;
import com.nimbleways.jidokabot.entities.Board;
import org.mapstruct.Mapper;

@Mapper
public class BoardMapper {

    public static final BoardMapper INSTANCE = new BoardMapper();


    public void boardDtoToBoard(final BoardDto boardDto, final Board target) {
        target.setId(boardDto.getId());
    }
}
