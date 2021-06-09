package com.example.CloudService.controller;

import com.example.CloudService.model.User;
import com.example.CloudService.service.UserService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthorizationsController {

    private final UserService userService;

    public AuthorizationsController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(String email, String password) throws NotFoundException {
        User user = userService.login(email, password);
        if(user ==  null) {
            throw new NotFoundException("User not found");
        }
        return user.getLogin();
    }
}
