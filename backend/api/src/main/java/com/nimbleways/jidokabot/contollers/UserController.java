package com.nimbleways.jidokabot.contollers;

import com.nimbleways.jidokabot.dto.UserDto;
import com.nimbleways.jidokabot.entities.User;
import com.nimbleways.jidokabot.services.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(final IUserService userService) {
        this.userService = userService;
    }
    @PostMapping("/new")
    public User createUser(@RequestBody @Valid  final UserDto userDto){
        return userService.createUser(userDto);
    }
}
