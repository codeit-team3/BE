package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookClubLikeEntityRepository extends JpaRepository<BookClubLikeEntity, Long> {
    Optional<BookClubLikeEntity> findByBookClubIdAndUserId(Long bookClubId, Long userId);
}
