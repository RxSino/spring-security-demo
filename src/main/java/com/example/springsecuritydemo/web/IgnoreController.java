package com.example.springsecuritydemo.web;

import com.example.springsecuritydemo.props.SignatureProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ignore")
public class IgnoreController {

    @Autowired
    private SignatureProps signatureProps;

    @RequestMapping("/signature")
    public String hello() {
        String token = signatureProps.getToken();
        String timestamp = "123456789";
        String nonce = "1234";

        String string = token + timestamp + nonce;
        return DigestUtils.md5DigestAsHex(string.getBytes());
    }
}
