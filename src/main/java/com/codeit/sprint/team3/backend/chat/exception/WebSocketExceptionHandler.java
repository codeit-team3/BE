package com.codeit.sprint.team3.backend.chat.exception;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(basePackages = "com.codeit.sprint.team3.backend.chat")
public class WebSocketExceptionHandler {

    @MessageExceptionHandler(UnauthorizedChatRoomAccessException.class)
    @SendToUser("/queue/errors")
    public String handleUnauthorizedAccess(UnauthorizedChatRoomAccessException ex) {
        return ex.getMessage(); // 에러 메시지를 사용자에게 전송
    }
}
