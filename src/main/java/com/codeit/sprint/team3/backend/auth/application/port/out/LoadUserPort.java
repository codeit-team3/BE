package com.codeit.sprint.team3.backend.auth.application.port.out;

import com.codeit.sprint.team3.backend.auth.domain.model.User;

public interface LoadUserPort {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
