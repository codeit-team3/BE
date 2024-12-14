package com.codeit.sprint.team3.backend.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain authSecurityFilterChain(HttpSecurity http) throws Exception {
        http.
                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/api/v1/auths/signup/**", "/api/v1/auths/signin/**", "/api/v1/auths/refresh/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class)
                .exceptionHandling(e ->
                        e.authenticationEntryPoint(authenticationEntryPoint));

        return http.build();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.h2.console.enabled", havingValue = "true")
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console());
    }

}
