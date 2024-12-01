package com.codeit.sprint.team3.backend.bookclub.application.port.out;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubMemberEntity;

public interface CommandBookClubMemberPort {
    void save(BookClubMemberEntity from);

    boolean existsByBookClubIdAndUserId(Long bookClubId, Long userId);

    void joinBookClub(Long bookClubId, Long userId);

    void leaveBookClub(Long bookClubId, Long userId);
}
