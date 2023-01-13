package com.nimbleways.jidokabot.services.implementations;

import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.entities.UsTimeRule;
import com.nimbleways.jidokabot.entities.User;
import com.nimbleways.jidokabot.fixtures.BoardFixtureHelper;
import com.nimbleways.jidokabot.repositories.BoardRepository;
import com.nimbleways.jidokabot.repositories.RuleRepository;
import com.nimbleways.jidokabot.repositories.UserRepository;
import com.nimbleways.jidokabot.services.IBoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements IBoardService {
    private final BoardRepository boardRepository;
    private final RuleRepository ruleRepository;
    private final UserRepository userRepository;


    @Override
    public Board getBoardById(final String idBoard) {
        return boardRepository.findById(idBoard).get();
    }

    @Override
    public Board save(final String boardDtoId, final String userId) {
        final User user = userRepository.getById(userId);
        final Board board = BoardFixtureHelper.aBoard().generate();
        board.setOwner(user);
        board.setId(boardDtoId);
        return boardRepository.save(board);
    }

    @Override
    public List<UsTimeRule> findTimeRulesOfBoard(final Board board) {
        return ruleRepository.getAllTimeRulesByBoard(board);
    }
}
