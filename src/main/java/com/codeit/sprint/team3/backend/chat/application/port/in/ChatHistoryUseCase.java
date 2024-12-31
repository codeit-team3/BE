package com.codeit.sprint.team3.backend.chat.application.port.in;

import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;

import java.util.List;

public interface ChatHistoryUseCase {
    List<ChatMessage> getRecentClubChatsForUser(Long userId);

    List<ChatMessage> getAllClubChats(Long userId, Long bookClubId);
}
