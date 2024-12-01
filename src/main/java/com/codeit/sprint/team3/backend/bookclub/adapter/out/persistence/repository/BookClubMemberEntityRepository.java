package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookClubMemberEntityRepository extends JpaRepository<BookClubMemberEntity, Integer> {
    boolean existsByBookClubIdAndUserIdAndIsInactiveFalse(Long bookClubId, Long userId);

    BookClubMemberEntity findByBookClubIdAndUserId(Long bookClubId, Long userId);
}
