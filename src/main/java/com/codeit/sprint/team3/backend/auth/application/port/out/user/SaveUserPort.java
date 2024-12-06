package com.codeit.sprint.team3.backend.auth.application.port.out.user;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface SaveUserPort {
    Long saveUser(RegisterUserRequest command);

    void saveUser(UserDetails userDetails);
}
