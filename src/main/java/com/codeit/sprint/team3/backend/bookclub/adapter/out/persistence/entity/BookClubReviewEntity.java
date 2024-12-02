package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
public class BookClubReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookClubId;
    private Long userId;
    private Integer rating;
    private String content;
    @ColumnDefault("false")
    private boolean isInactive;

    protected BookClubReviewEntity() {
    }

    private BookClubReviewEntity(Long bookClubId, Long userId, Integer rating, String content) {
        this.bookClubId = bookClubId;
        this.userId = userId;
        this.rating = rating;
        this.content = content;
    }

    public static BookClubReviewEntity from(BookClubReview bookClubReview) {
        return new BookClubReviewEntity(bookClubReview.getId(), bookClubReview.getUserId(), bookClubReview.getRating(), bookClubReview.getContent());
    }

    public BookClubReview toDomain() {
        return BookClubReview.of(id, bookClubId, userId, rating, content);
    }
}
