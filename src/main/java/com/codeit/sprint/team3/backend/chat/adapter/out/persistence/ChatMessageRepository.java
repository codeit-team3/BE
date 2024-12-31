package com.codeit.sprint.team3.backend.chat.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    Optional<ChatMessageEntity> findTopByBookClubIdOrderByDateDesc(Long bookClubId);

    Optional<List<ChatMessageEntity>> findByBookClubId(Long bookClubId);
}
