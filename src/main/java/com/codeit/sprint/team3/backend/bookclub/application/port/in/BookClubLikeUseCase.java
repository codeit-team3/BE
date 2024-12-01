package com.codeit.sprint.team3.backend.bookclub.application.port.in;

public interface BookClubLikeUseCase {
    void saveBookClubLike(Long bookClubId, Long userId);

    void deleteBookClubLike(Long bookClubId, Long userId);
}
