package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookClubReviewEntityRepository extends JpaRepository<BookClubReviewEntity, Long> {
}
