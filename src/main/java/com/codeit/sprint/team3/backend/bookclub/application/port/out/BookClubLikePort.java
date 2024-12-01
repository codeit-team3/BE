package com.codeit.sprint.team3.backend.bookclub.application.port.out;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubLike;

public interface BookClubLikePort {
    void saveBookClubLike(BookClubLike bookClubLike);

    void deleteBookClubLike(BookClubLike bookClubLike);
}
