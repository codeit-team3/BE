package com.codeit.sprint.team3.backend.chat.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
}
