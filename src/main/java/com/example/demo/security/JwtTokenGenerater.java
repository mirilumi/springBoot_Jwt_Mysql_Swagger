package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenGenerater {
    @Autowired
    JwtSettings settings;

    public Token createToken(UserContext userContext){
        if (StringUtils.isBlank(userContext.getUsername()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty())
            throw new IllegalArgumentException("User doesn't have any privileges");
        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        LocalDateTime localDateTime = LocalDateTime.now();
        String token = Jwts.builder()
                .setClaims(claims)
//                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(localDateTime
                        .plusMinutes(settings.getTokenExpirationTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
        return new Token(token);
    }
//    public Token createRefreshToken(){
//        return new Token();
//    }
}
