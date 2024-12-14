package com.codeit.sprint.team3.backend.auth.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserRequest;
import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auths")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auths")
public class RegisterUserController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    public ResponseEntity<Void> register(
            @RequestBody @Valid RegisterUserRequest request
    ) {
        registerUserUseCase.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
