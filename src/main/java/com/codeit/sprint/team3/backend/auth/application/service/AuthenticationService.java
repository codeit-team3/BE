package com.codeit.sprint.team3.backend.auth.application.service;

import com.codeit.sprint.team3.backend.auth.application.port.in.*;
import com.codeit.sprint.team3.backend.auth.application.port.out.token.RefreshTokenRemovePort;
import com.codeit.sprint.team3.backend.auth.application.port.out.token.RefreshTokenSavePort;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.exception.LoginFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationUseCase {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final LoadUserPort loadUserPort;
    private final RefreshTokenSavePort refreshTokenSavePort;
    private final RefreshTokenRemovePort refreshTokenRemovePort;


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException ex) { //AuthenticationException은 @ControllerAdvice에서 캐치 안됨.
            throw new LoginFailedException("잘못된 이메일 또는 비밀번호입니다");
        }

        var userDetails = loadUserPort.loadUserDetailsByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var accessToken = jwtService.generateAccessToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);

        refreshTokenSavePort.save(userDetails.getUsername(), refreshToken);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public LogoutResponse logout(LogoutRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //TODO 예외처리
        refreshTokenRemovePort.remove(username);

        return LogoutResponse.builder()
                .message("로그아웃 성공")
                .build();
    }
}
