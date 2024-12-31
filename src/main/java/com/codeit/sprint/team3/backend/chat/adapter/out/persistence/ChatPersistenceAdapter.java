package com.codeit.sprint.team3.backend.chat.adapter.out.persistence;

import com.codeit.sprint.team3.backend.chat.application.port.out.LoadRecentChatsPort;
import com.codeit.sprint.team3.backend.chat.application.port.out.SaveChatMessagePort;
import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatPersistenceAdapter implements SaveChatMessagePort, LoadRecentChatsPort {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    @Transactional
    public void save(List<ChatMessage> list) {
        chatMessageRepository.saveAll(
                list.stream().map(ChatMessageEntity::from).toList()
        );
    }

    @Override
    public ChatMessage loadRecentChats(Long bookClubId) {
        System.out.println("Load recent chats - bookClubId: " + bookClubId);
        return chatMessageRepository.findTopByBookClubIdOrderByDateDesc(bookClubId).toDomain();
    }
}
