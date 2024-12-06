package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubMember;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
public class BookClubMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookClubId;
    private Long userId;
    @ColumnDefault("false")
    private boolean isInactive;

    protected BookClubMemberEntity() {
    }

    private BookClubMemberEntity(Long bookClubId, Long userId) {
        this.bookClubId = bookClubId;
        this.userId = userId;
    }

    public static BookClubMemberEntity from(BookClubMember member) {
        return new BookClubMemberEntity(member.getBookClubId(), member.getUserId());
    }

    public static BookClubMemberEntity of(Long bookClubId, Long userId) {
        return new BookClubMemberEntity(bookClubId, userId);
    }

    public void inactivate() {
        isInactive = true;
    }
}
