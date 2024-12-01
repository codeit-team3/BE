package com.codeit.sprint.team3.backend.auth.application.port.out;

public interface AuthenticateUserPort {
    boolean authenticate(String email, String password);
}
