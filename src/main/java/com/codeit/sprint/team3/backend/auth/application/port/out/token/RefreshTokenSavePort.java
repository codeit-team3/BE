package com.codeit.sprint.team3.backend.auth.application.port.out.token;

public interface RefreshTokenSavePort {
    void save(String username, String refreshToken);
}
