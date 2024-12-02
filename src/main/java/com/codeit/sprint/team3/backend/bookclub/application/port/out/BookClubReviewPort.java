package com.codeit.sprint.team3.backend.bookclub.application.port.out;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;

import java.util.List;

public interface BookClubReviewPort {
    void saveBookClubReview(BookClubReview bookClubReview);

    List<BookClubReview> findAllByBookClubId(Long id);
}
