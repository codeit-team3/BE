package com.codeit.sprint.team3.backend.bookclub.application.port.in;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.codeit.sprint.team3.backend.bookclub.domain.model.BookClub;

import java.time.LocalDate;
import java.util.List;

public interface BookClubUseCase {
    void createBookClub(BookClub bookClub);

    List<BookClub> findBookClubsBy(BookClubType bookClubType, MeetingType meetingType, Integer memberLimit, String location, LocalDate targetDate);
}
