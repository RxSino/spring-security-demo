package com.example.springsecuritydemo.security;

import com.alibaba.fastjson.JSON;
import com.example.springsecuritydemo.data.ResponseData;
import com.example.springsecuritydemo.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private JwtTokenProvider tokenProvider;

    public AuthorizationFilter(AuthenticationManager manager, JwtTokenProvider provider) {
        super(manager);
        this.tokenProvider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        // 获取Authentication
        Authentication authentication = tokenProvider.getAuthentication(request);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } else {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(ResponseData.tokenError()));
        }
    }

}
