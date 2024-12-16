package com.codeit.sprint.team3.backend.bookclub.application.port.out;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;
import com.codeit.sprint.team3.backend.bookclub.domain.ScoredBookClubReview;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookClubReviewPort {
    void saveBookClubReview(BookClubReview bookClubReview);

    ScoredBookClubReview findAllByBookClubId(Long bookClubId, Pageable pageable, OrderType order);

    void deleteBookClubReview(Long bookClubId, Long userId, Long bookClubReviewId);

    List<BookClubReview> findUserReviews(Long userId, Pageable pageable, OrderType orderType);
}
