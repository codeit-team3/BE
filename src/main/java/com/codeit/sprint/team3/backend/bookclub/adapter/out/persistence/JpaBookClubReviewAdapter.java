package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubReviewEntity;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubReviewEntityRepository;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.BookClubReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaBookClubReviewAdapter implements BookClubReviewPort {
    private final BookClubReviewEntityRepository bookClubReviewRepository;

    @Override
    public void saveBookClubReview(Long bookClubId, Long userId, Integer rating, String content) {
        bookClubReviewRepository.save(BookClubReviewEntity.of(bookClubId, userId, rating, content));
    }
}
