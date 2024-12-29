package com.codeit.sprint.team3.backend.chat.application.port.out;

import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;

import java.util.List;

public interface SaveChatMessagePort {
    void save(List<ChatMessage> list);
}
