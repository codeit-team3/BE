package com.codeit.sprint.team3.backend.bookclub.application.port.in;

public interface BookClubReviewUseCase {
    void saveBookClubReview(Long bookClubId, Long userId, Integer rating, String content);
}
