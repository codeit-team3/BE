package com.codeit.sprint.team3.backend.bookclub.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
    private final Long bookClubId;
    private final Long userId;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(Long bookClubId, Long userId) {
        this.bookClubId = bookClubId;
        this.userId = userId;
    }

    public static Member of(Long bookClubId, Long userId) {
        return Member.builder()
                .bookClubId(bookClubId)
                .userId(userId)
                .build();
    }
}
