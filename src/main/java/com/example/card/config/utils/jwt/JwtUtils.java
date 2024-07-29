package com.example.card.config.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {
    //    @Value("${jwt.secret}")
    SecretKey publicKey;
//    long expirationTime;
    public TokenInfo parseUserToken(String token) {
        Claims claims = (Claims) Jwts
                .parser()
                .verifyWith(publicKey)
                .build()
                .parse(token)
                .getPayload();
        return TokenInfo.from(claims);
    }

    public JwtUtils(
            @Value("${jwt.secret}") String key
//            @Value("${jwt.expiration}") long expirationTime
    ) {
        this.publicKey = Keys.hmacShaKeyFor(key.getBytes());
//        this.expirationTime = expirationTime;
        ;
    }
}
