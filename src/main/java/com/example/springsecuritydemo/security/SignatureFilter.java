package com.example.springsecuritydemo.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class SignatureFilter extends BasicAuthenticationFilter {

    public SignatureFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String timestamp = request.getHeader("Signature");
        if (timestamp == null || timestamp.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("接口校验失败");
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean verifyTimestamp(String timestamp) {
        long timestampL = Long.parseLong(timestamp);
        long timestampN = new Date().getTime();
        return timestampL < timestampN;
    }

}