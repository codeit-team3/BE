package com.codeit.sprint.team3.backend.auth.application.port.in;

public interface AuthenticationUseCase {
    String authenticate(String username, String password);
}
