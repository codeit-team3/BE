package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;

import java.time.LocalDateTime;

public record ExpandedBookClubResponse(
        Long id,
        String title,
        String description,
        MeetingType meetingType,
        BookClubType bookClubType,
        LocalDateTime targetDate,
        LocalDateTime endDate,
        int memberLimit,
        String city,
        String town,
        String detailAddress,
        Long hostId,
        boolean isInactive,
        int memberCount,
        boolean isLiked,
        String imageUrl,
        String address,
        Double averageScore,
        boolean isJoined,
        String hostProfileImage,
        String hostNickname
) {
    public static ExpandedBookClubResponse from(BookClub bookClub) {
        return new ExpandedBookClubResponse(
                bookClub.getId(),
                bookClub.getTitle(),
                bookClub.getDescription(),
                bookClub.getMeetingType(),
                bookClub.getBookClubType(),
                bookClub.getTargetDate(),
                bookClub.getEndDate(),
                bookClub.getMemberLimit(),
                bookClub.getCity(),
                bookClub.getTown(),
                bookClub.getDetailAddress(),
                bookClub.getCreatedBy(),
                bookClub.isInactive(),
                bookClub.getMemberCount(),
                bookClub.isLiked(),
                bookClub.getImageUrl(),
                bookClub.getAddress(),
                bookClub.getRating(),
                bookClub.isJoined(),
                bookClub.getUserImage(),
                bookClub.getNickname()
        );
    }
}