package com.codeit.sprint.team3.backend.auth.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auths")
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationUseCase.authenticate(request));
    }

    @PostMapping("/signout")
    public ResponseEntity<LogoutResponse> signout(
            @RequestBody @Valid LogoutRequest request
    ) {
        return ResponseEntity.ok(authenticationUseCase.logout(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(
            @RequestBody @Valid RefreshRequest request
    ) {
        return ResponseEntity.ok(authenticationUseCase.refresh(request));
    }

}
