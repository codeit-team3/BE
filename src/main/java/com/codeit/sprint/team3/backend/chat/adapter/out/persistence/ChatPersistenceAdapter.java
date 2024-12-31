package com.codeit.sprint.team3.backend.chat.adapter.out.persistence;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubEntity;
import com.codeit.sprint.team3.backend.chat.application.port.out.LoadChatPort;
import com.codeit.sprint.team3.backend.chat.application.port.out.SaveChatMessagePort;
import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatPersistenceAdapter implements SaveChatMessagePort, LoadChatPort {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    @Transactional
    public void save(List<ChatMessage> list) {
        chatMessageRepository.saveAll(
                list.stream().map(ChatMessageEntity::from).toList()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ChatMessage loadRecentChat(Long bookClubId) {
        return chatMessageRepository
                .findTopByBookClubIdOrderByDateDesc(bookClubId)
                .orElse(new ChatMessageEntity())
                .toDomain();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessage> loadAllChat(Long bookClubId) {
        return chatMessageRepository
                .findByBookClubId(bookClubId)
                .orElse(new ArrayList<>())
                .stream().map(ChatMessageEntity::toDomain).toList();
    }
}
