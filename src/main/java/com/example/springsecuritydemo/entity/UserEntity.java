package com.example.springsecuritydemo.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserEntity {

    private String uuid;
    private String username;
    private String password;
    private Set<AuthorityEntity> authorities = new HashSet<>();

}
