package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity;

import com.codeit.sprint.team3.backend.auth.adapter.out.persistence.UserEntity;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "bookclub_reviews")
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
    private LocalDateTime createdAt;

    protected BookClubReviewEntity() {
    }

    private BookClubReviewEntity(Long bookClubId, Long userId, Integer rating, String content) {
        this.bookClubId = bookClubId;
        this.userId = userId;
        this.rating = rating;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public static BookClubReviewEntity from(BookClubReview bookClubReview) {
        return new BookClubReviewEntity(bookClubReview.getBookClubId(), bookClubReview.getUserId(), bookClubReview.getRating(), bookClubReview.getContent());
    }

    public BookClubReview toDomain() {
        return BookClubReview.of(id, bookClubId, userId, rating, content);
    }

    public BookClubReview toDomain(UserEntity userEntity) {
        return BookClubReview.of(id, bookClubId, userId, rating, content, userEntity.getCreatedAt().toLocalDateTime(), userEntity.getNickname(), userEntity.getImage());
    }

    public void inactivate() {
        isInactive = true;
    }
}
