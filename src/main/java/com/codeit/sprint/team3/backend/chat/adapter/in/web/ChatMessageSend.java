package com.codeit.sprint.team3.backend.chat.adapter.in.web;

import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;
import com.codeit.sprint.team3.backend.chat.domain.ChatType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ChatMessageSend {
    private Long id;
    private Long bookClubId;
    private LocalDateTime date;
    private Long userId;
    private String userNickname;
    private ChatType type;
    private String content;
    private String image;

    public static ChatMessageSend from(ChatMessage chatMessage, String image) {
        ChatMessageSend messageSend = new ChatMessageSend(
                chatMessage.getId(),
                chatMessage.getBookClubId(),
                chatMessage.getDate(),
                chatMessage.getUserId(),
                chatMessage.getUserNickname(),
                chatMessage.getType(),
                chatMessage.getContent(),
                image
        );
        return messageSend;
    }
}
