package com.nimbleways.jidokabot.repositories;

import com.nimbleways.jidokabot.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, String> {

}
