package com.example.CloudService.security;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public Long getUserId() {
        return 1L;
    }
}
