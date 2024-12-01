package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubLike;
import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"bookClubId", "userId"})
})
public class BookClubLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookClubId;
    private Long userId;

    protected BookClubLikeEntity() {
    }

    public BookClubLikeEntity(Long bookClubId, Long userId) {
        this.bookClubId = bookClubId;
        this.userId = userId;
    }

    public static BookClubLikeEntity from(BookClubLike bookClubLike) {
        return new BookClubLikeEntity(bookClubLike.getBookClubId(), bookClubLike.getUserId());
    }
}
