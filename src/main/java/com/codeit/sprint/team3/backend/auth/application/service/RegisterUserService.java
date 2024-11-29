package com.codeit.sprint.team3.backend.auth.application.service;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserCommand;
import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserUseCase;
import com.codeit.sprint.team3.backend.auth.application.port.out.CreateUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.exception.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final CreateUserPort createUserPort;
    private final LoadUserPort loadUserPort;

    @Override
    public Long register(RegisterUserCommand command) {
        if(loadUserPort.existsByEmail(command.getEmail())) {
            throw new EmailAlreadyExistsException(command.getEmail() + " 이미 가입된 이메일입니다.");
        }
        return createUserPort.createUser(command);
    }
}
