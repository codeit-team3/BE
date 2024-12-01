package com.codeit.sprint.team3.backend.bookclub.application.port.in;

import com.codeit.sprint.team3.backend.bookclub.domain.Member;

public interface BookClubMemberUseCase {
    void saveMember(Member member);

    void joinBookClub(Long bookClubId, Long userId);

    void leaveBookClub(Long bookClubId, Long userId);
}
