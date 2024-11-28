package com.codeit.sprint.team3.backend.bookclub.application.port.in;

import com.codeit.sprint.team3.backend.bookclub.domain.model.BookClub;

public interface BookClubUseCase {
    void createBookClub(BookClub bookClub);
}
