package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookClubDto {
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
    private final LocalDateTime createdAt;
    private final Boolean isInactive;
    private final Boolean hasImage;
    private final Boolean isLiked;
    private final Integer memberCount;
    private final String address;
    private final Boolean isJoined;
    private final String userImage;
    private final String nickname;

    @QueryProjection
    public BookClubDto(Long id, String title, String description, MeetingType meetingType, BookClubType bookClubType, LocalDateTime targetDate, LocalDateTime endDate, int memberLimit, String city, String town, String detailAddress, Long createdBy, LocalDateTime createdAt, Boolean isInactive, Boolean hasImage, String address, int memberCount, Boolean isLiked, Boolean isJoined, String userImage, String nickname) {
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
        this.createdAt = createdAt;
        this.isInactive = isInactive;
        this.hasImage = hasImage;
        this.address = address;
        this.memberCount = memberCount;
        this.isLiked = isLiked;
        this.isJoined = isJoined;
        this.userImage = userImage;
        this.nickname = nickname;
    }

    public BookClub toModel(String imageUrl, Double rating) {
        return BookClub.of(
                id,
                title,
                description,
                meetingType,
                bookClubType,
                targetDate,
                endDate,
                memberLimit,
                city,
                town,
                detailAddress,
                createdBy,
                memberCount,
                isInactive,
                isLiked,
                imageUrl,
                address,
                rating,
                isJoined,
                userImage,
                nickname
        );
    }
}
