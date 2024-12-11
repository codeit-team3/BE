package com.codeit.sprint.team3.backend.bookclub.application.port.out;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubMember;

public interface CommandBookClubMemberPort {
    void save(BookClubMember bookClubMember);

    boolean existsByBookClubIdAndUserId(Long bookClubId, Long userId);

    void joinBookClub(Long bookClubId, Long userId);

    void leaveBookClub(Long bookClubId, Long userId);
}
