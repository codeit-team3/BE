package com.codeit.sprint.team3.backend.auth.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserRequest;
import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auths")
public class RegisterUserController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> register(
            @Valid @RequestBody RegisterUserRequest request
    ) {

        registerUserUseCase.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "사용자 생성 성공"));
    }
}
