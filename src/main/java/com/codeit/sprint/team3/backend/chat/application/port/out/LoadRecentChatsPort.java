package com.codeit.sprint.team3.backend.chat.application.port.out;

import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;

public interface LoadRecentChatsPort {
    ChatMessage loadRecentChats(Long bookClubId);
}
