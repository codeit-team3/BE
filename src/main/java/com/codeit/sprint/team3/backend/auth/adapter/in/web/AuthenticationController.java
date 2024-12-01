package com.codeit.sprint.team3.backend.auth.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.AuthenticationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auths")
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signin(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        return ResponseEntity.ok(Map.of("token", authenticationUseCase.authenticate(email, password)));
    }

}
