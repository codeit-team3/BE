package com.codeit.sprint.team3.backend.bookclub.application.port.out;

import com.codeit.sprint.team3.backend.bookclub.domain.model.BookClub;

public interface CommandBookClubPort {
    BookClub saveBookClub(BookClub bookClub);
}
