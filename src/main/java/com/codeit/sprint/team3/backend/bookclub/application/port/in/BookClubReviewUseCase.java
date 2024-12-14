package com.codeit.sprint.team3.backend.bookclub.application.port.in;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookClubReviewUseCase {
    void saveBookClubReview(Long bookClubId, Long userId, Integer rating, String content);

    List<BookClubReview> getBookClubReviewsById(Long bookClubId, Pageable pageable, OrderType order);

    void deleteBookClubReview(Long bookClubId, Long userId, Long bookClubReviewId);
}
