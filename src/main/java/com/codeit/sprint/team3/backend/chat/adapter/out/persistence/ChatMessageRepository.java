package com.codeit.sprint.team3.backend.chat.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    ChatMessageEntity findTopByBookClubIdOrderByDateDesc(Long bookClubId);

    List<ChatMessageEntity> findByBookClubId(Long bookClubId);
}
