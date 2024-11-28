package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.model.BookClub;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class BookClubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BookClubType bookClubType;
    private LocalDate targetDate;
    private LocalDate endDate;
    private int memberLimit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected BookClubEntity() {
    }

    @Builder(access = AccessLevel.PRIVATE)
    private BookClubEntity(
            String title,
            BookClubType bookClubType,
            LocalDate targetDate,
            LocalDate endDate,
            int memberLimit,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.title = title;
        this.bookClubType = bookClubType;
        this.targetDate = targetDate;
        this.endDate = endDate;
        this.memberLimit = memberLimit;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static BookClubEntity from(BookClub bookClub) {
        return BookClubEntity.builder()
                .title(bookClub.getTitle())
                .bookClubType(bookClub.getBookClubType())
                .targetDate(bookClub.getTargetDate())
                .endDate(bookClub.getEndDate())
                .memberLimit(bookClub.getMemberLimit())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public BookClub toModel() {
        return BookClub.of(id, title, bookClubType, targetDate, endDate, memberLimit);
    }
}
