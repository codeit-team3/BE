package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;

public record BookClubResponse(
        Long id,
        String title,
        String description,
        MeetingType meetingType,
        BookClubType bookClubType,
        String targetDate,
        String endDate,
        int memberLimit,
        String town,
        int memberCount,
        boolean isLiked
) {
    public static BookClubResponse from(BookClub bookClub) {
        return new BookClubResponse(
                bookClub.getId(),
                bookClub.getTitle(),
                bookClub.getDescription(),
                bookClub.getMeetingType(),
                bookClub.getBookClubType(),
                bookClub.getTargetDate().toString(),
                bookClub.getEndDate().toString(),
                bookClub.getMemberLimit(),
                bookClub.getTown(),
                bookClub.getMemberCount(),
                bookClub.isLiked()
        );
    }
}
