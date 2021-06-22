package com.example.CloudService.repository;

import com.example.CloudService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    User findByLoginAndPassword(String login, String password);
    User findByLogin(String username);
}