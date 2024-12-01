package com.codeit.sprint.team3.backend.bookclub.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookClub {
    private final Long id;
    private final String title;
    private final String description;
    private final MeetingType meetingType;
    private final BookClubType bookClubType;
    private final LocalDate targetDate;
    private final LocalDate endDate;
    private final int memberLimit;
    private final String city;
    private final String town;
    private final Long createdBy;
    private final int memberCount;

    @Builder(access = AccessLevel.PRIVATE)
    private BookClub(Long id, String description, String title, MeetingType meetingType, BookClubType bookClubType, LocalDate targetDate, LocalDate endDate, int memberLimit, String city, String town, Long createdBy, int memberCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.meetingType = meetingType;
        this.bookClubType = bookClubType;
        this.targetDate = targetDate;
        this.endDate = endDate;
        this.memberLimit = memberLimit;
        this.city = city;
        this.town = town;
        this.createdBy = createdBy;
        this.memberCount = memberCount;
    }

    public static BookClub of(String title, String description, MeetingType meetingType, BookClubType bookClubType, LocalDate targetDate, LocalDate endDate, int memberLimit, String city, String town) {
        return BookClub.builder()
                .title(title)
                .description(description)
                .meetingType(meetingType)
                .bookClubType(bookClubType)
                .targetDate(targetDate)
                .endDate(endDate)
                .memberLimit(memberLimit)
                .city(city)
                .town(town)
                .build();
    }

    public static BookClub of(Long id, String title, String description, MeetingType meetingType, BookClubType bookClubType, LocalDate targetDate, LocalDate endDate, int memberLimit, String city, String town, Long createdBy) {
        return BookClub.builder()
                .id(id)
                .title(title)
                .description(description)
                .meetingType(meetingType)
                .bookClubType(bookClubType)
                .targetDate(targetDate)
                .endDate(endDate)
                .memberLimit(memberLimit)
                .city(city)
                .town(town)
                .createdBy(createdBy)
                .build();
    }

    public static BookClub of(Long id, String title, String description, MeetingType meetingType, BookClubType bookClubType, LocalDate targetDate, LocalDate endDate, int memberLimit, String city, String town, Long createdBy, int memberCount) {
        return BookClub.builder()
                .id(id)
                .title(title)
                .description(description)
                .meetingType(meetingType)
                .bookClubType(bookClubType)
                .targetDate(targetDate)
                .endDate(endDate)
                .memberLimit(memberLimit)
                .city(city)
                .town(town)
                .createdBy(createdBy)
                .memberCount(memberCount)
                .build();
    }
}
