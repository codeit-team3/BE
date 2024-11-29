package com.codeit.sprint.team3.backend.auth.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserCommand;
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
            @Valid //검증 실패 시 MethodArgumentNotValidException 발생 -> ExceptionHandler 에서 처리
            @RequestBody
            RegisterUserRequest request) {
        RegisterUserCommand command = new RegisterUserCommand(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getNickName(),
                request.getDescription()
        );

        registerUserUseCase.register(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "사용자 생성 성공"));
    }
}
