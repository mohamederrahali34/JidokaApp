package com.nimbleways.jidokabot.repositories;

import com.nimbleways.jidokabot.entities.Board;
import com.nimbleways.jidokabot.entities.Rule;
import com.nimbleways.jidokabot.entities.StockRule;
import com.nimbleways.jidokabot.entities.UsTimeRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface RuleRepository extends JpaRepository<Rule, UUID> {
    List<Rule> findByBoard(Board board);
    @Query("select r from Rule r where r.id= :id and r.type = :type ")
    Rule getRuleByTypeAndId(String type, UUID id);
    @Query( "select r from UsTimeRule r where r.board = ?1 and r.timeRuleColumnName = ?2 ")
    List<UsTimeRule> getTimeRulesByBoardAndColumnName(Board board, String columnName);
    @Query( "select r from StockRule r where r.board = ?1 and r.stockRuleColumnName = ?2 ")
    List<StockRule> getStockRulesByBoardAndColumnName(Board board, String columnName);
    @Query( "select r from UsTimeRule r where r.board = ?1")
    List<UsTimeRule> getAllTimeRulesByBoard(Board board);
    @Query("select r from UsTimeRule r where r.board = ?1 and r.type =?2 and r.timeRuleColumnName = ?3 and r.active = true")
    List<UsTimeRule> getActiveTimeRulesByBoardAndTypeAndColumnName(Board board, String type, String columnName);
    @Query("select r from StockRule r where r.board = ?1 and r.type =?2 and r.stockRuleColumnName = ?3 and r.active = true")
    List<StockRule> getActiveStockRulesByBoardAndTypeAndColumnName(Board board, String type, String columnName);
    @Query("select r from UsTimeRule r where r.id = ?1")
    UsTimeRule getTimeRuleById(UUID id);
}
