package com.codeit.sprint.team3.backend.auth.application.port.out;

public interface LoadUserPort {
    boolean existsByEmail(String email);
}
