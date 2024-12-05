package com.codeit.sprint.team3.backend.auth.adapter.out.persistence;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserRequest;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.CreateUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.domain.model.User;
import com.codeit.sprint.team3.backend.auth.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements CreateUserPort, LoadUserPort {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long createUser(RegisterUserRequest command) {
        UserEntity userEntity = new UserEntity(
                command.getName(),
                command.getEmail(),
                passwordEncoder.encode(command.getPassword()),
                command.getNickname(),
                command.getDescription(),
                Role.USER
        );

        return userRepository.save(userEntity).getId();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email)).toUser();
    }

    @Override
    public Optional<UserDetails> loadUserDetailsByEmail(String email) {
        return userRepository.findByEmail(email).map(userEntity -> userEntity);
    }

}
