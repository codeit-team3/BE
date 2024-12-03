package com.codeit.sprint.team3.backend.auth.application.port.out;

import com.codeit.sprint.team3.backend.auth.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface LoadUserPort {
    boolean existsByEmail(String email);

    User loadUserByEmail(String email);

    //Spring Security 용
    Optional<UserDetails> loadUserDetailsByEmail(String email);
}
