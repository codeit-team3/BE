package com.codeit.sprint.team3.backend.auth.application.port.in;

import com.codeit.sprint.team3.backend.common.validation.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterUserCommand extends SelfValidating<RegisterUserCommand> {
    @NotBlank
    private final String name;

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

    @NotBlank
    private final String nickname;

    private final String description;

    public RegisterUserCommand(
            String name,
            String email,
            String password,
            String nickname,
            String description) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.description = description;

        validateSelf(); // 검증 로직 수행
    }
}
