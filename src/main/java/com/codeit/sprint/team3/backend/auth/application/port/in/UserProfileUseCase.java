package com.codeit.sprint.team3.backend.auth.application.port.in;

import com.codeit.sprint.team3.backend.auth.domain.model.User;

public interface UserProfileUseCase {
    User getUserByEmail(String email);
    User updateUserProfile(String email, UpdateUserProfileCommand command);
}
