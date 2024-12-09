package com.codeit.sprint.team3.backend.auth.adapter.out.persistence;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserRequest;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.RegisterUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.UpdateUserPort;
import com.codeit.sprint.team3.backend.auth.domain.model.User;
import com.codeit.sprint.team3.backend.auth.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements RegisterUserPort, LoadUserPort, UpdateUserPort {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long register(RegisterUserRequest command) {
        ZonedDateTime createdAt = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        UserEntity userEntity = new UserEntity(
                command.getName(),
                command.getEmail(),
                passwordEncoder.encode(command.getPassword()),
                "",
                command.getNickname(),
                command.getDescription()==null?"":command.getDescription(),
                createdAt,
                createdAt,
                Role.USER
        );

        return userRepository.save(userEntity).getId();
    }

    @Override
    public void update(User user) {
        UserEntity userEntity = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("회원정보 수정 실패"));

        userEntity.setNickname(user.getNickname());
        userEntity.setImage(user.getImage());
        userEntity.setUpdatedAt(user.getUpdatedAt());

        userRepository.save(userEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email)).toDomain();
    }

    @Override
    public Optional<UserDetails> loadUserDetailsByEmail(String email) {
        return userRepository.findByEmail(email).map(userEntity -> userEntity);
    }

}
