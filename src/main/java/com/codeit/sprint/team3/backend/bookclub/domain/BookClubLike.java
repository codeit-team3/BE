package com.codeit.sprint.team3.backend.bookclub.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookClubLike {
    private final Long bookClubId;
    private final Long userId;

    @Builder(access = AccessLevel.PRIVATE)
    private BookClubLike(Long bookClubId, Long userId) {
        this.bookClubId = bookClubId;
        this.userId = userId;
    }

    public static BookClubLike of(Long bookClubId, Long userId) {
        return BookClubLike.builder()
                .bookClubId(bookClubId)
                .userId(userId)
                .build();
    }
}
