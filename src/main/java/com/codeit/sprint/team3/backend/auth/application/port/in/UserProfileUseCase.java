package com.codeit.sprint.team3.backend.auth.application.port.in;

import com.codeit.sprint.team3.backend.auth.domain.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserProfileUseCase {
    User getUserByEmail(String email);
    User getUserById(Long userId);
    User updateUserProfile(String email, MultipartFile image, UpdateUserProfileCommand command);
}
