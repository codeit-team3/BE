package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;

import java.time.LocalDateTime;

public record BookClubReviewResponse(
        Long id,
        Long userId,
        Long bookClubId,
        Integer rating,
        String content,
        LocalDateTime createdAt,
        String nickname,
        String userImage,
        String bookClubTitle,
        String bookClubImageUrl,
        BookClubType bookClubType
) {
    public static BookClubReviewResponse from(BookClubReview bookClubReview) {
        return new BookClubReviewResponse(
                bookClubReview.getId(),
                bookClubReview.getUserId(),
                bookClubReview.getBookClubId(),
                bookClubReview.getRating(),
                bookClubReview.getContent(),
                bookClubReview.getCreatedAt(),
                bookClubReview.getNickname(),
                bookClubReview.getUserImage(),
                bookClubReview.getBookClubTitle(),
                bookClubReview.getBookClubImageUrl(),
                bookClubReview.getBookClubType()
        );
    }
}
