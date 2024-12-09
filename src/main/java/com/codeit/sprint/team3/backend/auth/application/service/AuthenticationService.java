package com.codeit.sprint.team3.backend.auth.application.service;

import com.codeit.sprint.team3.backend.auth.application.port.in.*;
import com.codeit.sprint.team3.backend.auth.application.port.out.token.RefreshTokenLoadPort;
import com.codeit.sprint.team3.backend.auth.application.port.out.token.RefreshTokenRemovePort;
import com.codeit.sprint.team3.backend.auth.application.port.out.token.RefreshTokenSavePort;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.exception.InvalidRefreshTokenException;
import com.codeit.sprint.team3.backend.auth.exception.LoginFailedException;
import com.codeit.sprint.team3.backend.common.security.ExpiredRefreshTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationUseCase {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final LoadUserPort loadUserPort;

    private final RefreshTokenSavePort tokenSavePort;
    private final RefreshTokenRemovePort tokenRemovePort;
    private final RefreshTokenLoadPort tokenLoadPort;


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

        tokenSavePort.save(userDetails.getUsername(), refreshToken);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public LogoutResponse logout(LogoutRequest request) {
        String username = null;
        try {
            username = jwtService.extractUsername(request.getRefreshToken());
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            throw new InvalidRefreshTokenException("잘못된 Refresh Token입니다.");
        } catch (ExpiredJwtException ignored) {
            //만료된 리프레시 토큰도 로그아웃 성공 응답.
        }

        //TODO 예외처리 - 지울 리프레시 토큰이 없다면?
        if(username != null) {
            tokenRemovePort.remove(username);
        }

        return LogoutResponse.builder()
                .message("로그아웃 성공")
                .build();
    }

    @Override
    public RefreshResponse refresh(RefreshRequest request) {
        String username;
        try {
            username = jwtService.extractUsername(request.getRefreshToken());
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            throw new InvalidRefreshTokenException("잘못된 Refresh Token입니다.");
        } catch (ExpiredJwtException e) {
            throw new ExpiredRefreshTokenException("만료된 Refresh Token입니다. 다시 로그인하세요.");
        }

        String refreshToken = tokenLoadPort.load(username);
        if(refreshToken == null) {
            throw new ExpiredRefreshTokenException("만료된 Refresh Token입니다. 다시 로그인하세요.");
        }

        var userDetails = loadUserPort.loadUserDetailsByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var accessToken = jwtService.generateAccessToken(userDetails);

        return RefreshResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
