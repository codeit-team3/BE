package com.codeit.sprint.team3.backend.chat.adapter.in.web.response;

import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;

import java.time.LocalDate;
import java.util.*;

public record HistoryResponses(List<HistoryResponse> historyResponses){
    public static HistoryResponses from(List<ChatMessage> messages) {
        List<HistoryResponse> histories = new ArrayList<>();

        TreeMap<LocalDate, List<ChatMessage>> messagesByDate = new TreeMap<>();

        messages.forEach(m -> {
            LocalDate date = m.getDate().toLocalDate();
            if(!messagesByDate.containsKey(date)) {
                messagesByDate.put(date, new ArrayList<>());
            }
            messagesByDate.get(date).add(m);
        });

        messagesByDate.forEach((date, lst) -> {
            lst.sort(Comparator.comparing(ChatMessage::getDate));
            histories.add(new HistoryResponse(date, lst));
        });

        return new HistoryResponses(histories);
    }
}
