package com.nimbleways.jidokabot.repositories;

import com.nimbleways.jidokabot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
