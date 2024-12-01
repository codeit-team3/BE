package com.codeit.sprint.team3.backend.bookclub.application.port.in;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;

public interface BookClubLikeUseCase {
    void saveBookClubLike(Long bookClubId, Long userId);

    void deleteBookClubLike(Long bookClubId, Long userId);
}
