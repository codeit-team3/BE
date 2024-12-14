package com.codeit.sprint.team3.backend.auth.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.UpdateUserProfileCommand;
import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auths")
@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileUseCase userProfileUseCase;

    @GetMapping("/user")
    @Operation(summary = "회원 정보 확인", description = "회원 정보를 확인합니다. 헤더에 액세스 토큰을 포함해야합니다.")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<UserProfileDto> getUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.ok(UserProfileDto.from(userProfileUseCase.getUserByEmail(userEmail)));
    }

    @PostMapping("/user")
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다. 헤더에 액세스 토큰을 포함해야합니다.")
    @SecurityRequirement(name = "JWT")
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
