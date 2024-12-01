package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubMemberEntity;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubMemberEntityRepository;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.CommandBookClubMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaBookClubMemberAdapter implements CommandBookClubMemberPort {
    private final BookClubMemberEntityRepository bookClubMemberEntityRepository;

    @Override
    public void save(BookClubMemberEntity bookClubMemberEntity) {
        bookClubMemberEntityRepository.save(bookClubMemberEntity);
    }

    @Override
    public boolean existsByBookClubIdAndUserId(Long bookClubId, Long userId) {
        return bookClubMemberEntityRepository.existsByBookClubIdAndUserIdAndIsInactiveFalse(bookClubId, userId);
    }

    @Override
    public void joinBookClub(Long bookClubId, Long userId) {
        bookClubMemberEntityRepository.save(BookClubMemberEntity.of(bookClubId, userId));
    }

    @Override
    public void leaveBookClub(Long bookClubId, Long userId) {
        BookClubMemberEntity bookClubMemberEntity = bookClubMemberEntityRepository.findByBookClubIdAndUserId(bookClubId, userId);
        bookClubMemberEntity.inactivate();
    }
}
