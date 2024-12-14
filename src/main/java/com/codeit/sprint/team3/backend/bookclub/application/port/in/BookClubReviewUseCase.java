package com.codeit.sprint.team3.backend.bookclub.application.port.in;

import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;
import com.codeit.sprint.team3.backend.bookclub.domain.ScoredBookClubReview;
import org.springframework.data.domain.Pageable;

public interface BookClubReviewUseCase {
    void saveBookClubReview(Long bookClubId, Long userId, Integer rating, String content);

    ScoredBookClubReview getBookClubReviewsById(Long bookClubId, Pageable pageable, OrderType order);

    void deleteBookClubReview(Long bookClubId, Long userId, Long bookClubReviewId);
}
