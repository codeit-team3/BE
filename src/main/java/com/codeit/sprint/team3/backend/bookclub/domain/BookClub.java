package com.codeit.sprint.team3.backend.bookclub.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookClub {
    private final Long id;
    private final String title;
    private final String description;
    private final MeetingType meetingType;
    private final BookClubType bookClubType;
    private final LocalDateTime targetDate;
    private final LocalDateTime endDate;
    private final int memberLimit;
    private final String city;
    private final String town;
    private final String detailAddress;
    private final Long createdBy;
    private final boolean isInactive;
    private final int memberCount;
    private final boolean isLiked;
    private final String imageUrl;
    private final String address;
    private final Double rating;
    private final boolean isJoined;
    private final String userImage;
    private final String nickname;

    @Builder(access = AccessLevel.PRIVATE)
    private BookClub(
            Long id,
            String description,
            String title,
            MeetingType meetingType,
            BookClubType bookClubType,
            LocalDateTime targetDate,
            LocalDateTime endDate,
            int memberLimit,
            String city,
            String town,
            String detailAddress,
            Long createdBy,
            int memberCount,
            boolean isInactive,
            boolean isLiked,
            String imageUrl,
            String address,
            Double rating,
            boolean isJoined,
            String userImage,
            String nickname
    ) {
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
        this.detailAddress = detailAddress;
        this.createdBy = createdBy;
        this.memberCount = memberCount;
        this.isInactive = isInactive;
        this.isLiked = isLiked;
        this.imageUrl = imageUrl;
        this.address = address;
        this.rating = rating;
        this.isJoined = isJoined;
        this.userImage = userImage;
        this.nickname = nickname;
    }

    //Request
    public static BookClub of(String title, String description, MeetingType meetingType, BookClubType bookClubType, LocalDateTime targetDate, LocalDateTime endDate, int memberLimit, String city, String town, String detailAddress, String address) {
        return BookClub.builder()
                .title(title)
                .description(description)
                .meetingType(meetingType)
                .bookClubType(bookClubType)
                .targetDate(targetDate)
                .endDate(endDate)
                .memberLimit(memberLimit)
                .town(town)
                .city(city)
                .detailAddress(detailAddress)
                .address(address)
                .build();
    }

    //Entity to domain
    public static BookClub of(
            Long id,
            String title,
            String description,
            MeetingType meetingType,
            BookClubType bookClubType,
            LocalDateTime targetDate,
            LocalDateTime endDate,
            int memberLimit,
            String town,
            Long createdBy,
            boolean isInactive,
            String imageUrl,
            String address,
            Double rating
    ) {
        return BookClub.builder()
                .id(id)
                .title(title)
                .description(description)
                .meetingType(meetingType)
                .bookClubType(bookClubType)
                .targetDate(targetDate)
                .endDate(endDate)
                .memberLimit(memberLimit)
                .town(town)
                .createdBy(createdBy)
                .isInactive(isInactive)
                .imageUrl(imageUrl)
                .address(address)
                .rating(rating)
                .build();
    }

    //Dto to domain
    public static BookClub of(
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
            Long createdBy,
            int memberCount,
            boolean isInactive,
            boolean isLiked,
            String imageUrl,
            String address,
            Double rating,
            boolean isJoined,
            String userImage,
            String nickname
    ) {
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
                .detailAddress(detailAddress)
                .createdBy(createdBy)
                .memberCount(memberCount)
                .isInactive(isInactive)
                .isLiked(isLiked)
                .imageUrl(imageUrl)
                .address(address)
                .rating(rating)
                .isJoined(isJoined)
                .userImage(userImage)
                .nickname(nickname)
                .build();
    }
}
