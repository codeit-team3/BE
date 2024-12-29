package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;

import java.time.LocalDateTime;

public record BookClubReviewResponse(
        Long id,
        Long userId,
        Long bookClubId,
        Integer rating,
        String content,
        String nickname,
        String image,
        LocalDateTime createdAt
) {
    public static BookClubReviewResponse from(BookClubReview bookClubReview) {
        return new BookClubReviewResponse(
                bookClubReview.getId(),
                bookClubReview.getUserId(),
                bookClubReview.getBookClubId(),
                bookClubReview.getRating(),
                bookClubReview.getContent(),
                bookClubReview.getNickname(),
                bookClubReview.getImage(),
                bookClubReview.getCreatedAt()
        );
    }
}
