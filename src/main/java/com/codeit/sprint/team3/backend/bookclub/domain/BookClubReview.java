package com.codeit.sprint.team3.backend.bookclub.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookClubReview {
    private final Long id;
    private final Long userId;
    private final Long bookClubId;
    private final Integer rating;
    private final String content;

    @Builder(access = AccessLevel.PRIVATE)
    private BookClubReview(Long id, Long userId, Long bookClubId, Integer rating, String content) {
        this.id = id;
        this.userId = userId;
        this.bookClubId = bookClubId;
        this.rating = rating;
        this.content = content;
    }

    public static BookClubReview of(Long bookClubId, Long userId, Integer rating, String content) {
        return BookClubReview.builder()
                .userId(userId)
                .bookClubId(bookClubId)
                .rating(rating)
                .content(content)
                .build();
    }

    public static BookClubReview of(Long id, Long bookClubId, Long userId, Integer rating, String content) {
        return BookClubReview.builder()
                .userId(userId)
                .bookClubId(bookClubId)
                .rating(rating)
                .content(content)
                .build();
    }
}
