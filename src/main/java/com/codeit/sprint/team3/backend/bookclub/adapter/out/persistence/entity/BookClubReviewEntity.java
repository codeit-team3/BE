package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BookClubReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookClubId;
    private Long userId;
    private Integer rating;
    private String content;

    protected BookClubReviewEntity() {
    }

    private BookClubReviewEntity(Long bookClubId, Long userId, Integer rating, String content) {
        this.bookClubId = bookClubId;
        this.userId = userId;
        this.rating = rating;
        this.content = content;
    }

    public static BookClubReviewEntity of(Long bookClubId, Long userId, Integer rating, String content) {
        return new BookClubReviewEntity(bookClubId, userId, rating, content);
    }
}
