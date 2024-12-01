package com.codeit.sprint.team3.backend.auth.adapter.out.persistence;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserCommand;
import com.codeit.sprint.team3.backend.auth.application.port.out.CreateUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.LoadUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements CreateUserPort, LoadUserPort {

    private final UserRepository userRepository;

    @Override
    public Long createUser(RegisterUserCommand command) {
        UserEntity userEntity = new UserEntity(
                command.getName(),
                command.getEmail(),
                command.getPassword(),
                command.getNickName(),
                command.getDescription()
        );

        return userRepository.save(userEntity).getId();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
