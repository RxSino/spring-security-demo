package com.example.springsecuritydemo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDetails loadUserByUid(String uid) {
        return new User("root", "123456", new HashSet<>());
    }
}
