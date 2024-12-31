package com.codeit.sprint.team3.backend.chat.exception;

public class UnauthorizedChatRoomAccessException extends RuntimeException{
    public UnauthorizedChatRoomAccessException(String message) {
        super(message);
    }
}
