package com.codeit.sprint.team3.backend.bookclub.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookClubReview {
    private final Long id;
    private final Long userId;
    private final Long bookClubId;
    private final Integer rating;
    private final String content;
    private final LocalDateTime createdAt;
    private final String nickname;
    private final String userImage;
    private final String bookClubTitle;
    private final String bookClubImageUrl;
    private final BookClubType bookClubType;

    @Builder(access = AccessLevel.PRIVATE)
    private BookClubReview(Long id, Long userId, Long bookClubId, Integer rating, String content, LocalDateTime createdAt, String nickname, String userImage, String bookClubTitle, String bookClubImageUrl, BookClubType bookClubType) {
        this.id = id;
        this.userId = userId;
        this.bookClubId = bookClubId;
        this.rating = rating;
        this.content = content;
        this.createdAt = createdAt;
        this.nickname = nickname;
        this.userImage = userImage;
        this.bookClubTitle = bookClubTitle;
        this.bookClubImageUrl = bookClubImageUrl;
        this.bookClubType = bookClubType;
    }

    //create
    public static BookClubReview of(Long bookClubId, Long userId, Integer rating, String content) {
        return BookClubReview.builder()
                .userId(userId)
                .bookClubId(bookClubId)
                .rating(rating)
                .content(content)
                .build();
    }

    public static BookClubReview of(Long id, Long bookClubId, Long userId, Integer rating, String content, LocalDateTime createdAt, String nickname, String userImage, String bookClubTitle, String bookClubImageUrl, BookClubType bookClubType) {
        return BookClubReview.builder()
                .id(id)
                .userId(userId)
                .bookClubId(bookClubId)
                .rating(rating)
                .content(content)
                .createdAt(createdAt)
                .nickname(nickname)
                .userImage(userImage)
                .bookClubTitle(bookClubTitle)
                .bookClubImageUrl(bookClubImageUrl)
                .bookClubType(bookClubType)
                .build();
    }
}
