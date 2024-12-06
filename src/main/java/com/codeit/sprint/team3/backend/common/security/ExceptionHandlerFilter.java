package com.codeit.sprint.team3.backend.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            doFilter(request, response, filterChain);
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            setJsonBody(response, Map.of(
                    "code", "EXPIRED",
                    "message", "만료된 토큰입니다."
            ));
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            setJsonBody(response, Map.of(
                    "code", "UNAUTHORIZED",
                    "message", "잘못된 토큰입니다."
            ));
        } catch (UsingRefreshTokenException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            setJsonBody(response, Map.of(
                    "code", "UNAUTHORIZED",
                    "message", "Access Token을 사용하세요."
            ));
        }
    }

    private void setJsonBody(HttpServletResponse response, Map<String, String> body) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
