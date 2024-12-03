package com.codeit.sprint.team3.backend.auth.application.port.in;

public interface AuthenticationUseCase {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
