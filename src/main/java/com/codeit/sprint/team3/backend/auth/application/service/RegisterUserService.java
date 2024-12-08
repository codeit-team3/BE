package com.codeit.sprint.team3.backend.auth.application.service;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserRequest;
import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserUseCase;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.RegisterUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.exception.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterUserService implements RegisterUserUseCase {

    private final RegisterUserPort createUserPort;
    private final LoadUserPort loadUserPort;

    @Override
    public Long register(RegisterUserRequest command) {
        if(loadUserPort.existsByEmail(command.getEmail())) {
            throw new EmailAlreadyExistsException(command.getEmail() + " 이미 가입된 이메일입니다.");
        }
        return createUserPort.register(command);
    }
}
