package com.codeit.sprint.team3.backend.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain authSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auths/signup").permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
    }

    /* 각 도메인 별로 FilterChain 을 추가한다.
    ex)
    @Bean
    public SecurityFilterChain bookClubSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                .requestMatchers("/bookclub/getClub").permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
    }
    */

}
