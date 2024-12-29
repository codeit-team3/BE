package com.codeit.sprint.team3.backend.chat.config;

import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.application.service.JwtService;
import com.codeit.sprint.team3.backend.auth.domain.model.User;
import com.codeit.sprint.team3.backend.common.security.UsingRefreshTokenException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtWebSocketInterceptor implements HandshakeInterceptor {

    private final LoadUserPort loadUserPort;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes
    ) throws Exception {
        String query = request.getURI().getQuery();
        if (query == null || !query.contains("token=") || query.split("token=")[1].isEmpty()) {
            // 쿼리 파라미터가 없거나 'token'이 없는 경우
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false; // 핸드셰이크 중단
        }

        String jwt = query.split("token=")[1];

        try {
            String userEmail = jwtService.extractUsername(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if(userEmail == null) {
                return false;
            }

            if(jwtService.isTokenInvalid(jwt, userDetails)) {
                throw new JwtException("잘못된 토큰입니다.");
            }
            if(jwtService.isRefreshToken(jwt)) {
                throw new UsingRefreshTokenException("Access Token을 사용하세요.");
            }

            User user = loadUserPort.loadUserByEmail(userEmail);
            attributes.put("user", user);
            return true;
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }
}
