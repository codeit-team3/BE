package com.codeit.sprint.team3.backend.chat.adapter.out.persistence;

import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;
import com.codeit.sprint.team3.backend.chat.domain.ChatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "chat_message",
        indexes = @Index(name = "idx_book_club", columnList = "bookClubId")
)
public class ChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //배치 저장을 위해서는 Sequence 혹은 Table 전략을 사용해야한다.
    private Long id;

    private Long bookClubId;

    private LocalDateTime date;

    private Long userId;

    private String userNickname;

    private ChatType type;

    private String content;

    public static ChatMessageEntity from(ChatMessage chatMessage){
        return new ChatMessageEntity(
                chatMessage.getId(),
                chatMessage.getBookClubId(),
                chatMessage.getDate(),
                chatMessage.getUserId(),
                chatMessage.getUserNickname(),
                chatMessage.getType(),
                chatMessage.getContent()
        );
    }

}
