package com.codeit.sprint.team3.backend.auth.application.service;

import com.codeit.sprint.team3.backend.auth.application.port.in.UpdateUserProfileCommand;
import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.UpdateUserPort;
import com.codeit.sprint.team3.backend.auth.domain.model.User;
import com.codeit.sprint.team3.backend.bookclub.domain.ImageFactory;
import com.codeit.sprint.team3.backend.common.application.port.out.FileUploadPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileService implements UserProfileUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    private final FileUploadPort fileUploadPort;
    private final ImageFactory imageFactory;

    @Override
    public User getUserByEmail(String email) {
        return loadUserPort.loadUserByEmail(email);
    }

    @Override
    public User getUserById(Long userId) {
        return loadUserPort.loadUserById(userId);
    }

    @Override
    public User updateUserProfile(String email, MultipartFile image, UpdateUserProfileCommand command) {
        User user = loadUserPort.loadUserByEmail(email);

        if(image != null) {
            fileUploadPort.uploadImageToS3(image, "users/" + user.getId(), "image.jpg", "jpg");
            String url = imageFactory.createImageUrl("users", user.getId(), "image.jpg", true);
            user.setImage(url);
        }

        user.update(command);

        updateUserPort.update(user);
        return user;
    }
}
