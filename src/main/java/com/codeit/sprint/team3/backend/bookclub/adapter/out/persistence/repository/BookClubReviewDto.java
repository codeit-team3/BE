package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record BookClubReviewDto(
        Long id,
        Long bookClubId,
        Long userId,
        Integer rating,
        String content,
        boolean isInactive,
        LocalDateTime createdAt,
        String nickname,
        String userImage,
        String bookClubTitle,
        Boolean hasImage,
        BookClubType bookClubType
) {
    @QueryProjection
    public BookClubReviewDto {
    }

    public BookClubReview toModel(String bookClubImageUrl) {
        return BookClubReview.of(
                id,
                bookClubId,
                userId,
                rating,
                content,
                createdAt,
                nickname,
                userImage,
                bookClubTitle,
                bookClubImageUrl,
                bookClubType
        );
    }
}
