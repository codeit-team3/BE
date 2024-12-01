package com.codeit.sprint.team3.backend.bookclub.application.port.out;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;

public interface BookClubReviewPort {
    void saveBookClubReview(BookClubReview bookClubReview);
}
