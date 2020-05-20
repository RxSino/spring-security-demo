package com.example.springsecuritydemo.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    @Autowired
    private JwtUserDetailService userDetailService;

    public String createToken(String username) {
        return "fake token";
    }

    public String resolveToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) throws Exception {
        return true;
    }

    public String getUsername(String token) throws Exception {
        return "root";
    }

    public Authentication getAuthentication(String token) throws Exception {
        String username = getUsername(token);
        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(username, "", userDetails.getAuthorities());
    }

}
