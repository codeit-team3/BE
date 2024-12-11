package com.codeit.sprint.team3.backend.bookclub.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookClubMember {
    private final Long bookClubId;
    private final Long userId;

    @Builder(access = AccessLevel.PRIVATE)
    private BookClubMember(Long bookClubId, Long userId) {
        this.bookClubId = bookClubId;
        this.userId = userId;
    }

    public static BookClubMember of(Long bookClubId, Long userId) {
        return BookClubMember.builder()
                .bookClubId(bookClubId)
                .userId(userId)
                .build();
    }
}
