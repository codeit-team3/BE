package com.codeit.sprint.team3.backend.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //private final JwtWebSocketInterceptor jwtWebSocketInterceptor;

    @Override //STOMP 엔드포인트 설정
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                //.addInterceptors(jwtWebSocketInterceptor) //인터셉터를 통한 로그인 사용자 식별가능
                .setAllowedOriginPatterns("*").withSockJS();
    }

    /**
     * 개인메시지는 '/queue/private/{userId} 로 라우팅
     * 수신자는 '/user/queue/private/{userId}'를 구독
     * 발신자는 '/app/chat/private' 으로 메시지를 보냄
     * */
    @Override //메시지 라우팅
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
    }

}
