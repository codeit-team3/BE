package com.codeit.sprint.team3.backend.bookclub.application.port.out;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface QueryBookClubPort {
    List<BookClub> findBookClubsBy(BookClubType bookClubType, MeetingType meetingType, Integer memberLimit, String location, LocalDate targetDate);

    Optional<BookClub> findById(Long bookClubId);
}
