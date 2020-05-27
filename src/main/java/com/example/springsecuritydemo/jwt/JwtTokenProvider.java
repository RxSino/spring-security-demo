package com.example.springsecuritydemo.jwt;

import com.example.springsecuritydemo.service.UserService;
import com.example.springsecuritydemo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    @Autowired
    private UserService userService;

    public Authentication getAuthentication(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }
        String token = authorization.substring(7);
        String uid = JwtUtils.getUid(token);
        UserDetails userDetails = userService.loadUserByUid(uid);
        return new UsernamePasswordAuthenticationToken(uid, "", userDetails.getAuthorities());
    }

}
