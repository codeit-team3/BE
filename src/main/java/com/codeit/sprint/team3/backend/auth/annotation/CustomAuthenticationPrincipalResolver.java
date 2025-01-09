package com.codeit.sprint.team3.backend.auth.annotation;

import com.codeit.sprint.team3.backend.auth.adapter.out.persistence.UserEntity;
import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import com.codeit.sprint.team3.backend.auth.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
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
        loadUserPort.loadUserDetailsByEmail(email)
                .orElse(UserEntity.empty());
        validateLoginRequired(customAuthenticationPrincipal.required());
        return null;
    }

    private void validateLoginRequired(boolean required) {
        if (required) {
        }
    }
}
