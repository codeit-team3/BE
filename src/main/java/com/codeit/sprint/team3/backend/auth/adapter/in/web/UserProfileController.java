package com.codeit.sprint.team3.backend.auth.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.UpdateUserProfileCommand;
import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileUseCase userProfileUseCase;

    @GetMapping("/user")
    public ResponseEntity<UserProfileDto> getUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.ok(UserProfileDto.from(userProfileUseCase.getUserByEmail(userEmail)));
    }

    @PostMapping("/user")
    public ResponseEntity<UserProfileDto> updateUser(
            @RequestBody @Valid UpdateUserProfileCommand command
    ) {
        validateCommand(command);

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.ok(UserProfileDto.from(userProfileUseCase.updateUserProfile(userEmail, command)));
    }

    private void validateCommand(UpdateUserProfileCommand command) {
        if (isBlank(command.getNickname()) && isBlank(command.getImage())) {
            throw new IllegalArgumentException("닉네임과 이미지는 모두 공백일 수 없습니다.");
        }
    }

    private boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

}
