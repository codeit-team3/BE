package com.codeit.sprint.team3.backend.auth.application.port.out.user;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserRequest;

public interface RegisterUserPort {
    Long register(RegisterUserRequest command);
}
