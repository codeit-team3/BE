package com.codeit.sprint.team3.backend.auth.adapter.out.persistence;

import com.codeit.sprint.team3.backend.auth.application.port.out.token.RefreshTokenLoadPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.token.RefreshTokenRemovePort;
import com.codeit.sprint.team3.backend.auth.application.port.out.token.RefreshTokenSavePort;
import com.codeit.sprint.team3.backend.common.JwtConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RefreshTokenPersistenceAdapter implements RefreshTokenSavePort, RefreshTokenLoadPort, RefreshTokenRemovePort {

    private final RedisTemplate<String, String> redisTemplate;
    private final JwtConfig jwtConfig;

    @Override
    public void save(String username, String refreshToken) {
        redisTemplate.opsForValue().set(
                jwtConfig.getRefreshTokenKeyPrefix() + username,
                refreshToken,
                jwtConfig.getRefreshExpiration(),
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public String load(String username) {
        return redisTemplate.opsForValue().get(jwtConfig.getRefreshTokenKeyPrefix() + username);
    }

    @Override
    public void remove(String username) {
        redisTemplate.delete(jwtConfig.getRefreshTokenKeyPrefix() + username);
    }

}
