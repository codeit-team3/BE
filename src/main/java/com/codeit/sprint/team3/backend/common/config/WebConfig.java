package com.codeit.sprint.team3.backend.common.config;

import com.codeit.sprint.team3.backend.common.annotation.CustomAuthenticationPrincipalResolver;
import com.codeit.sprint.team3.backend.auth.application.port.out.user.LoadUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final LoadUserPort loadUserPort;

    @Bean
    public HandlerMethodArgumentResolver loginUserResolver() {
        return new CustomAuthenticationPrincipalResolver(loadUserPort);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserResolver());
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }
}