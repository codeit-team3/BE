package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.model.BookClub;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateBookClubRequest(
        @NotBlank(message = "타이틀은 필수입니다.")
        String title,
        @NotBlank(message = "북클럽 타입은 필수입니다.")
        String bookClubType,
        @NotBlank(message = "모임 날짜는 필수입니다.")
        LocalDate targetDate,
        @NotBlank(message = "마감 날짜는 필수입니다.")
        LocalDate endDate,
        @NotBlank(message = "모집 정원은 필수입니다.")
        int memberLimit,
        String city,
        String town
) {
    public BookClub toDomain() {
        return BookClub.of(title, BookClubType.from(bookClubType), targetDate, endDate, memberLimit, city, town);
    }
}
