package com.nimbleways.jidokabot.services.implementations;

import com.nimbleways.jidokabot.dto.UserDto;
import com.nimbleways.jidokabot.dto.trellodto.TrelloUser;
import com.nimbleways.jidokabot.entities.User;
import com.nimbleways.jidokabot.repositories.UserRepository;
import com.nimbleways.jidokabot.services.IProjectManagementService;
import com.nimbleways.jidokabot.services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;
    private IProjectManagementService projectManagementService;

    @Override
    public User createUser(final UserDto userDto) {
        final TrelloUser trelloUser = projectManagementService.getConnectedUser(userDto.getToken());
        final User user = new User();
        user.setToken(userDto.getToken());
        user.setName(trelloUser.getFullName());
        user.setId(trelloUser.getId());
        return userRepository.save(user);
    }
}
