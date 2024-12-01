package com.codeit.sprint.team3.backend.bookclub.application.port.out;

public interface BookClubReviewPort {
    void saveBookClubReview(Long bookClubId, Long userId, Integer rating, String content);
}
