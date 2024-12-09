package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;

public record BookClubReviewResponse(
        Long id,
        Long userId,
        Long bookClubId,
        Integer rating,
        String content
) {
    public static BookClubReviewResponse from(BookClubReview bookClubReview) {
        return new BookClubReviewResponse(
                bookClubReview.getId(),
                bookClubReview.getUserId(),
                bookClubReview.getBookClubId(),
                bookClubReview.getRating(),
                bookClubReview.getContent()
        );
    }
}
