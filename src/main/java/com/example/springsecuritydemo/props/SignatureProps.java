package com.example.springsecuritydemo.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.signature")
public class SignatureProps {

    private String token;

}
