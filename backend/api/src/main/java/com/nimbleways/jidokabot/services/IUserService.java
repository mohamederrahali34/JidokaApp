package com.nimbleways.jidokabot.services;

import com.nimbleways.jidokabot.dto.UserDto;
import com.nimbleways.jidokabot.entities.User;

public interface IUserService {
    User createUser(UserDto userDto);
}
