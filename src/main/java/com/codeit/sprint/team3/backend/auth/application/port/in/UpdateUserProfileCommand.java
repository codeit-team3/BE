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
    private String nickname;

    private String image;

    private String description;
}
