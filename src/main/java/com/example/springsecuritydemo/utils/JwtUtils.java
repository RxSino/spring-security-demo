package com.example.springsecuritydemo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class JwtUtils {

    // 秘钥
    private static final String RxSecret = "rx-secret";
    // 发行人
    private static final String RxIssuer = "rx-issuer";
    // 主题
    private static final String RxSubject = "rx-subject";
    // 用户
    private static final String RxAudience = "rx-audience";
    // 用户ID
    private static final String RxUid = "uid";

    public static String create(String uid) {
        try {
            Date nowDate = new Date();
            // 过期时间
            long rxExpireTime = 24 * 60 * 60 * 1000;
            Date expireDate = new Date(nowDate.getTime() + rxExpireTime);
            Algorithm algorithm = Algorithm.HMAC256(RxSecret);
            return JWT.create()
                    .withIssuer(RxIssuer)
                    .withSubject(RxSubject)
                    .withAudience(RxAudience)
                    .withClaim(RxUid, uid)
                    .withIssuedAt(nowDate)
                    .withExpiresAt(expireDate)
                    .withJWTId(UUID.randomUUID().toString())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean verify(String jwtToken) {
        if (Objects.isNull(jwtToken))
            return false;
        try {
            Algorithm algorithm = Algorithm.HMAC256(RxSecret);
            JWT.require(algorithm).build().verify(jwtToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static DecodedJWT decode(String jwtToken) {
        try {
            return JWT.decode(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUid(String jwtToken) {
        try {
            return JWT.decode(jwtToken).getClaim(RxUid).asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
