package com.codeit.sprint.team3.backend.common.annotation;

import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.domain.model.User;
import com.codeit.sprint.team3.backend.auth.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@RequiredArgsConstructor
public class CustomAuthenticationPrincipalResolver implements HandlerMethodArgumentResolver {
    private final LoadUserPort loadUserPort;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CustomAuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        CustomAuthenticationPrincipal customAuthenticationPrincipal = Objects.requireNonNull(parameter.getParameterAnnotation(CustomAuthenticationPrincipal.class));
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user;
        try {
            user = loadUserPort.loadUserByEmail(email);
        } catch(UserNotFoundException e) {
            user = User.getEmpty();
        }
        validateLoginRequired(customAuthenticationPrincipal.required(), user);
        return user;
    }

    private void validateLoginRequired(boolean required, User user) {
        if (required && user.isEmpty()) {
            throw new UserNotFoundException("유저 정보를 찾을수 없습니다.");
        }
    }
}
