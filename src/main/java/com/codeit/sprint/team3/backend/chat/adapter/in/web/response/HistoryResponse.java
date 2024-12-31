package com.codeit.sprint.team3.backend.chat.adapter.in.web.response;

import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;

import java.time.LocalDate;
import java.util.List;

public record HistoryResponse(
        LocalDate date,
        List<ChatMessage> messages
) {
    public static HistoryResponse of(LocalDate date, List<ChatMessage> messages) {
        return new HistoryResponse(date, messages);
    }
}
