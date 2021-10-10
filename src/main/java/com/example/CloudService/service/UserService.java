package com.example.CloudService.service;

import com.example.CloudService.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User login(String login, String password);
}
