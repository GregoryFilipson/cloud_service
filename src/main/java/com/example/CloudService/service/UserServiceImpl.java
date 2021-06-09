
package com.example.CloudService.service;

import com.example.CloudService.model.User;
import com.example.CloudService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService { //подкл Spring Security и добавить шифрование пароля

    @Autowired
    UserRepository userRepository;
//
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;


    public User login(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }
}
