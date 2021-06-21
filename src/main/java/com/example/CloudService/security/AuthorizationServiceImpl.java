package com.example.CloudService.security;

import com.example.CloudService.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    UserRepository userRepository;

    @Override
    public Long getUserId() {
        return 1L;
    }
}
