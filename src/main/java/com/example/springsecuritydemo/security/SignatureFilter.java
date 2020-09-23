package com.example.springsecuritydemo.security;

import com.alibaba.fastjson.JSON;
import com.example.springsecuritydemo.data.ResponseData;
import com.example.springsecuritydemo.props.SignatureProps;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.DigestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class SignatureFilter extends OncePerRequestFilter {

    private SignatureProps signatureProps;

    public SignatureFilter(SignatureProps signatureProps) {
        this.signatureProps = signatureProps;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String signature = request.getHeader("Signature");
        String timestamp = request.getHeader("Timestamp");
        String nonce = request.getHeader("Nonce");

        if (signature == null || timestamp == null || nonce == null || !sign(timestamp, nonce, signature)) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(ResponseData.signatureError()));
            return;
        }
        chain.doFilter(request, response);
    }

    // 注意参数顺序： token + timestamp + nonce
    private boolean sign(String timestamp, String nonce, String signature) {
        String token = signatureProps.getToken();
        String string = token + timestamp + nonce;
        String rightSignature = DigestUtils.md5DigestAsHex(string.getBytes());
        return signature.equals(rightSignature);
    }

}