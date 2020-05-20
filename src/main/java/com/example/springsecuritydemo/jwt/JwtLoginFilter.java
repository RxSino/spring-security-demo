package com.example.springsecuritydemo.jwt;

import com.alibaba.fastjson.JSON;
import com.example.springsecuritydemo.data.HttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
        super.setFilterProcessesUrl("/login");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // 默认实现会发生重定向
        // super.successfulAuthentication(request, response, chain, authResult);

        HttpResponse httpResponse = new HttpResponse();
        httpResponse.code = "0";
        httpResponse.message = "登陆成功，返回TOKEN";
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(httpResponse));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        // super.unsuccessfulAuthentication(request, response, failed);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("登陆失败");
    }
}
