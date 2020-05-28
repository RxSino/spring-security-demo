package com.example.springsecuritydemo.security;

import com.example.springsecuritydemo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findOneByUsername(username)
                .map(userEntity -> createUser(username, userEntity))
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    // 模拟数据库，查找用户
    private Optional<UserEntity> findOneByUsername(String username) {
        UserEntity entity = new UserEntity();
        entity.setUsername("root");
        entity.setPassword(passwordEncoder.encode("123456"));
        entity.setAuthorities(new HashSet<>());
        return Optional.of(entity);
    }

    private User createUser(String username, UserEntity entity) {
        List<GrantedAuthority> authorities = entity.getAuthorities()
                .stream()
                .map(authorityEntity -> new SimpleGrantedAuthority(authorityEntity.getRole()))
                .collect(Collectors.toList());
        return new User(entity.getUsername(), entity.getPassword(), authorities);
    }
}
