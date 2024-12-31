package com.codeit.sprint.team3.backend.chat.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.domain.model.User;
import com.codeit.sprint.team3.backend.chat.application.port.in.ChatHistoryUseCase;
import com.codeit.sprint.team3.backend.chat.application.port.in.SaveChatMessageUseCase;
import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;
import com.codeit.sprint.team3.backend.chat.domain.ChatType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    private final SaveChatMessageUseCase saveChatMessageUseCase;
    private final ChatHistoryUseCase chatHistoryUseCase;

    @MessageMapping("/group-chat/{chatRoomId}/sendMessage")
    public void sendMessage(
            @Header(name = "simpSessionAttributes") Map<String, Object> sessionAttributes,
            @DestinationVariable String chatRoomId, //경로에서 추출
            @Payload ChatMessageReceived chatMessageReceived
    ) {
        User user = (User) sessionAttributes.get("user");

        ChatMessage chatMessage = new ChatMessage(
                Long.parseLong(chatRoomId),
                ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime(),
                user.getId(),
                user.getNickname(),
                ChatType.CHAT,
                chatMessageReceived.getContent()
        );

        String destination = "/topic/group-chat/" + chatRoomId;

        messagingTemplate.convertAndSend(destination, chatMessage);
        saveChatMessageUseCase.save(chatMessage);
    }

    @MessageMapping("/group-chat/recent")
    @SendToUser("/queue/chatHistory")
    public List<ChatMessage> getHistory(
            @Header(name = "simpSessionAttributes") Map<String, Object> sessionAttributes
    ) {
        User user = (User) sessionAttributes.get("user");

        return chatHistoryUseCase.getRecentClubChatsForUser(user.getId());
    }

}
