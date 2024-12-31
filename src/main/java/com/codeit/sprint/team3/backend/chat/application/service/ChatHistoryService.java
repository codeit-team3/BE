package com.codeit.sprint.team3.backend.chat.application.service;

import com.codeit.sprint.team3.backend.bookclub.application.port.out.QueryBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;
import com.codeit.sprint.team3.backend.chat.application.port.in.ChatHistoryUseCase;
import com.codeit.sprint.team3.backend.chat.application.port.out.LoadRecentChatsPort;
import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatHistoryService implements ChatHistoryUseCase {

    private final QueryBookClubPort queryBookClubPort;
    private final LoadRecentChatsPort loadRecentChatsPort;

    @Override
    public List<ChatMessage> getRecentClubChatsForUser(Long userId) {
        return queryBookClubPort
                .findUserJoinedBookClubs(userId, OrderType.DESC, Pageable.ofSize(100), false)
                .stream()
                .map(BookClub::getId)
                .map(loadRecentChatsPort::loadRecentChats)
                .toList();
    }
}
