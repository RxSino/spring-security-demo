package com.example.springsecuritydemo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDetails loadUserByUid(String uid) {
        return null;
    }
}
