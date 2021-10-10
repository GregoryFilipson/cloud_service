package com.example.CloudService.service;

import com.example.CloudService.model.User;
import com.example.CloudService.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private List <GrantedAuthority> corsAllowedMethods = Collections.emptyList();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User byLogin = userRepository.findByLogin(s);
        List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
        listAuthorities.addAll(corsAllowedMethods);
        return new org.springframework.security.core.userdetails.User(byLogin.getLogin(),
                byLogin.getPassword(), listAuthorities);
    }
}
