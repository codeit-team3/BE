package com.codeit.sprint.team3.backend.auth.application.port.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@AllArgsConstructor
public class UpdateUserProfileCommand {
    @NotNull(message = "닉네임은 null일 수 없습니다.")
    private String nickname;

    @NotNull(message = "이미지는 null일 수 없습니다.")
    private String image;
}
