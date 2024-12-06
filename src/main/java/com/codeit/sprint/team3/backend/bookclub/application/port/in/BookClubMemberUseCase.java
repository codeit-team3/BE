package com.codeit.sprint.team3.backend.bookclub.application.port.in;

public interface BookClubMemberUseCase {
    void joinBookClub(Long bookClubId, Long userId);

    void leaveBookClub(Long bookClubId, Long userId);
}
