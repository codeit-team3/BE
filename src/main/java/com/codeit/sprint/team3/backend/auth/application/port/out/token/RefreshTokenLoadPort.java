package com.codeit.sprint.team3.backend.auth.application.port.out.token;

public interface RefreshTokenLoadPort {
    public String load(String username);
}
