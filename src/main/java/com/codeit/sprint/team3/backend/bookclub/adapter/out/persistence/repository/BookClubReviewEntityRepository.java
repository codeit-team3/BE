package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookClubReviewEntityRepository extends JpaRepository<BookClubReviewEntity, Long> {
    Optional<BookClubReviewEntity> getByIdAndBookClubIdAndUserIdAndInactiveFalse(Long id, Long bookClubId, Long userId);
}
