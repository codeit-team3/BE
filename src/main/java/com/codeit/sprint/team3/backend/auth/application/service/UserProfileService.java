package com.codeit.sprint.team3.backend.auth.application.service;

import com.codeit.sprint.team3.backend.auth.application.port.in.UpdateUserProfileCommand;
import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.UpdateUserPort;
import com.codeit.sprint.team3.backend.auth.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileService implements UserProfileUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    @Override
    public User getUserByEmail(String email) {
        return loadUserPort.loadUserByEmail(email);
    }

    @Override
    public User getUserById(Long userId) {
        return loadUserPort.loadUserById(userId);
    }

    @Override
    public User updateUserProfile(String email, UpdateUserProfileCommand command) {
        User user = loadUserPort.loadUserByEmail(email);

        user.update(command);

        updateUserPort.update(user);
        return user;
    }
}
