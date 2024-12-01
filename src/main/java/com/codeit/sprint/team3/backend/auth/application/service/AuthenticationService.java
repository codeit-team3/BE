package com.codeit.sprint.team3.backend.auth.application.service;

import com.codeit.sprint.team3.backend.auth.application.port.in.AuthenticationUseCase;
import com.codeit.sprint.team3.backend.auth.application.port.out.AuthenticateUserPort;
import com.codeit.sprint.team3.backend.auth.exception.LoginFailedException;
import com.codeit.sprint.team3.backend.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationUseCase {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticateUserPort authenticateUserPort;

    @Override
    public String authenticate(String email, String password) {
        if(!authenticateUserPort.authenticate(email, password)) {
            throw new LoginFailedException("Invalid email or password");
        }

        return jwtTokenProvider.createToken(email, "USER");
    }
}
