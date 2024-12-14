package com.codeit.sprint.team3.backend.auth.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.*;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auths")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auths")
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/signin")
    @Operation(summary = "사용자 로그인", description = "사용자 인증 및 액세스 토큰, 리프레시 토큰 반환")
    public ResponseEntity<AuthenticationResponse> signin(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationUseCase.authenticate(request));
    }

    @PostMapping("/signout")
    @Operation(summary = "사용자 로그아웃", description = "사용자를 로그아웃합니다. 리프레시 토큰이 만료됩니다.")
    public ResponseEntity<LogoutResponse> signout(
            @RequestBody @Valid LogoutRequest request
    ) {
        return ResponseEntity.ok(authenticationUseCase.logout(request));
    }

    @PostMapping("/refresh")
    @Operation(summary = "액세스 토큰 재발급", description = "유효한 리프레시 토큰을 통해 액세스 토큰을 재발급합니다.")
    public ResponseEntity<RefreshResponse> refresh(
            @RequestBody @Valid RefreshRequest request
    ) {
        return ResponseEntity.ok(authenticationUseCase.refresh(request));
    }

}
