package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateBookClubRequest(
        @NotBlank(message = "타이틀은 필수입니다.")
        String title,
        @NotBlank(message = "상세설명은 필수입니다.")
        String description,
        @NotBlank(message = "북클럽 타입은 필수입니다.")
        String bookClubType,
        @NotBlank(message = "온/오프라인 여부는 필수입니다.")
        String meetingType,
        @NotNull(message = "모임 날짜는 필수입니다.")
        LocalDate targetDate,
        @NotNull(message = "마감 날짜는 필수입니다.")
        LocalDate endDate,
        @NotNull(message = "모집 정원은 필수입니다.")
        Integer memberLimit,
        String city,
        String town
) {
    public BookClub toDomain() {
        return BookClub.of(title, description, MeetingType.getCommandType(meetingType), BookClubType.getCommandType(bookClubType), targetDate, endDate, memberLimit, city, town);
    }
}
