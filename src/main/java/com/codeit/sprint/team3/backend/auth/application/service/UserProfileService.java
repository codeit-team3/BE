package com.codeit.sprint.team3.backend.auth.application.service;

import com.codeit.sprint.team3.backend.auth.adapter.out.persistence.UserEntity;
import com.codeit.sprint.team3.backend.auth.application.port.in.UpdateUserProfileCommand;
import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.SaveUserPort;
import com.codeit.sprint.team3.backend.auth.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileService implements UserProfileUseCase {

    private final SaveUserPort saveUserPort;
    private final LoadUserPort loadUserPort;

    @Override
    public User getUserByEmail(String email) {
        return loadUserPort.loadUserByEmail(email);
    }

    @Override
    public User updateUserProfile(String email, UpdateUserProfileCommand command) {
        //UserEntity를 의존하지 않고 처리하는 방법?
        UserEntity userEntity = (UserEntity) loadUserPort.loadUserDetailsByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        String nickname = command.getNickname();
        String image = command.getImage();
        if(nickname != null) {
            userEntity.setNickname(nickname);
        }
        if(image != null) {
            userEntity.setImage(image);
        }
        userEntity.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        saveUserPort.saveUser(userEntity);

        return userEntity.toUser();
    }
}
