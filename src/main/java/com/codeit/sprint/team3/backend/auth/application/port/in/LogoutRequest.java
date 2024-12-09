package com.codeit.sprint.team3.backend.auth.application.port.in;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest {
    @NotBlank(message = "accessToken은 필수값입니다.")
    private String accessToken;

    @NotBlank(message = "refreshToken은 필수값입니다.")
    private String refreshToken;
}
