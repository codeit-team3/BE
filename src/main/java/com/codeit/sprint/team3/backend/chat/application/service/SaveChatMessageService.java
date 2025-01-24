package com.codeit.sprint.team3.backend.chat.application.service;

import com.codeit.sprint.team3.backend.chat.application.port.in.SaveChatMessageUseCase;
import com.codeit.sprint.team3.backend.chat.application.port.out.SaveChatMessagePort;
import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class SaveChatMessageService implements SaveChatMessageUseCase {
    private final SaveChatMessagePort saveChatMessagePort;

    @Override
    public void save(ChatMessage chatMessage) {
        saveChatMessagePort.save(chatMessage);
    }
}
