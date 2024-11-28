package com.codeit.sprint.team3.backend.bookclub.domain.model;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookClub {
    private final Long id;
    private final String title;
    private final BookClubType bookClubType;
    private final LocalDate targetDate;
    private final LocalDate endDate;
    private final int memberLimit;

    @Builder
    private BookClub(Long id,String title, BookClubType bookClubType, LocalDate targetDate, LocalDate endDate, int memberLimit) {
        this.id = id;
        this.title = title;
        this.bookClubType = bookClubType;
        this.targetDate = targetDate;
        this.endDate = endDate;
        this.memberLimit = memberLimit;
    }

    public static BookClub of(String title, BookClubType bookClubType, LocalDate targetDate, LocalDate endDate, int memberLimit) {
        return BookClub.builder()
                .title(title)
                .bookClubType(bookClubType)
                .targetDate(targetDate)
                .endDate(endDate)
                .memberLimit(memberLimit)
                .build();
    }

    public static BookClub of(Long id, String title, BookClubType bookClubType, LocalDate targetDate, LocalDate endDate, int memberLimit) {
        return BookClub.builder()
                .id(id)
                .title(title)
                .bookClubType(bookClubType)
                .targetDate(targetDate)
                .endDate(endDate)
                .memberLimit(memberLimit)
                .build();
    }
}
