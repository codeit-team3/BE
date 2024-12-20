package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public class BookClubDto {
    private final Long id;
    private final String title;
    private final String description;
    private final MeetingType meetingType;
    private final BookClubType bookClubType;
    private final LocalDateTime targetDate;
    private final LocalDateTime endDate;
    private final int memberLimit;
    private final String town;
    private final Long createdBy;
    private final LocalDateTime createdAt;
    private final Integer memberCount;
    private final Boolean isInactive;
    private final Boolean isLiked;

    @QueryProjection
    public BookClubDto(Long id, String title, String description, MeetingType meetingType, BookClubType bookClubType, LocalDateTime targetDate, LocalDateTime endDate, int memberLimit, String town, Long createdBy, LocalDateTime createdAt, Boolean isInactive, int memberCount, Boolean isLiked) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.meetingType = meetingType;
        this.bookClubType = bookClubType;
        this.targetDate = targetDate;
        this.endDate = endDate;
        this.memberLimit = memberLimit;
        this.town = town;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.isInactive = isInactive;
        this.memberCount = memberCount;
        this.isLiked = isLiked;
    }

    public BookClub toModel() {
        return BookClub.of(id, title, description, meetingType, bookClubType, targetDate, endDate, memberLimit, town, createdBy, memberCount, isInactive, isLiked);
    }
}
