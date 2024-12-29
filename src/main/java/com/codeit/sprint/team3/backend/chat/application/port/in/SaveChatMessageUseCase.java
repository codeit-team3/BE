package com.codeit.sprint.team3.backend.chat.application.port.in;

import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;

public interface SaveChatMessageUseCase {
    public void save(ChatMessage chatMessage);
}
