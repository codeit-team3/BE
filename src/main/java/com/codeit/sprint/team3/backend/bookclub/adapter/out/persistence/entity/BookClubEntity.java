package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
public class BookClubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private MeetingType meetingType;
    private BookClubType bookClubType;
    private LocalDateTime targetDate; // 모임 날짜
    private LocalDateTime endDate; // 모임 마감 날짜
    private int memberLimit;
    private String town;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ColumnDefault("false")
    private Boolean isInactive;

    protected BookClubEntity() {
    }

    @Builder(access = AccessLevel.PRIVATE)
    private BookClubEntity(
            String title,
            String description,
            MeetingType meetingType,
            BookClubType bookClubType,
            LocalDateTime targetDate,
            LocalDateTime endDate,
            int memberLimit,
            String town,
            Long createdBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean isInactive
    ) {
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
        this.updatedAt = updatedAt;
        this.isInactive = isInactive;
    }

    public static BookClubEntity of(BookClub bookClub, Long userId) {
        return BookClubEntity.builder()
                .title(bookClub.getTitle())
                .description(bookClub.getDescription())
                .meetingType(bookClub.getMeetingType())
                .bookClubType(bookClub.getBookClubType())
                .targetDate(bookClub.getTargetDate())
                .endDate(bookClub.getEndDate())
                .memberLimit(bookClub.getMemberLimit())
                .town(bookClub.getTown())
                .createdBy(userId)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isInactive(false)
                .build();
    }

    public BookClub toModel() {
        return BookClub.of(id, description, title, meetingType, bookClubType, targetDate, endDate, memberLimit, town, createdBy);
    }

    public void delete() {
        this.isInactive = true;
    }
}
