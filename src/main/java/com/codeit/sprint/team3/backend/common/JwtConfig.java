package com.codeit.sprint.team3.backend.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access_expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh_expiration}")
    private long refreshExpiration;

    private final String refreshTokenKeyPrefix = "refreshToken:";
}
