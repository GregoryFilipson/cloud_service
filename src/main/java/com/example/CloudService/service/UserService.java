package com.example.CloudService.service;

import com.example.CloudService.model.User;

public interface UserService {
    User login(String login, String password);
}
