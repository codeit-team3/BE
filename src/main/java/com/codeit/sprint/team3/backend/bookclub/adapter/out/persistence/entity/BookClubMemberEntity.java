package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity;

import com.codeit.sprint.team3.backend.bookclub.domain.model.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class BookClubMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookClubId;
    private Long userId;

    protected BookClubMemberEntity() {
    }

    private BookClubMemberEntity(Long bookClubId, Long userId) {
        this.bookClubId = bookClubId;
        this.userId = userId;
    }

    public static BookClubMemberEntity from(Member member) {
        return new BookClubMemberEntity(member.getBookClubId(), member.getUserId());
    }
}
