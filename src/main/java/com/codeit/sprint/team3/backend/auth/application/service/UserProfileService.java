package com.codeit.sprint.team3.backend.auth.application.service;

import com.codeit.sprint.team3.backend.auth.application.port.in.UpdateUserProfileCommand;
import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.UpdateUserPort;
import com.codeit.sprint.team3.backend.auth.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

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
    public User updateUserProfile(String email, UpdateUserProfileCommand command) {
        User user = loadUserPort.loadUserByEmail(email);

        String nickname = command.getNickname();
        String image = command.getImage();
        if(nickname != null) {
            user.setNickname(nickname);
        }
        if(image != null) {
            user.setImage(image);
        }
        user.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        updateUserPort.update(user);
        return user;
    }
}
