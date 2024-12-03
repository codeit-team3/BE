package com.codeit.sprint.team3.backend.auth.application.service;

import com.codeit.sprint.team3.backend.auth.application.port.in.AuthenticationRequest;
import com.codeit.sprint.team3.backend.auth.application.port.in.AuthenticationResponse;
import com.codeit.sprint.team3.backend.auth.application.port.in.AuthenticationUseCase;
import com.codeit.sprint.team3.backend.auth.application.port.out.LoadUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationUseCase {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final LoadUserPort loadUserPort;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var userDetails = loadUserPort.loadUserDetailsByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var jwtToken = jwtService.generateToken(userDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
