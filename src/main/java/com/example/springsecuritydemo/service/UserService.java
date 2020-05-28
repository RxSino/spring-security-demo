package com.example.springsecuritydemo.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {


    UserDetails loadUserByUid(String uid);

}
