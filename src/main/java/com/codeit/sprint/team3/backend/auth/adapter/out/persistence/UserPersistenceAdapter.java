package com.codeit.sprint.team3.backend.auth.adapter.out.persistence;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserCommand;
import com.codeit.sprint.team3.backend.auth.application.port.out.AuthenticateUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.CreateUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.domain.model.User;
import com.codeit.sprint.team3.backend.auth.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements CreateUserPort, LoadUserPort, AuthenticateUserPort {

    private final UserRepository userRepository;

    @Override
    public Long createUser(RegisterUserCommand command) {
        UserEntity userEntity = new UserEntity(
                command.getName(),
                command.getEmail(),
                command.getPassword(),
                command.getNickname(),
                command.getDescription()
        );

        return userRepository.save(userEntity).getId();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email)).toUser();
    }

    @Override
    public boolean authenticate(String email, String password) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        if(!userEntity.isPresent()) {
            return false;
        }

        return userEntity.get().getPassword().equals(password);
    }
}
