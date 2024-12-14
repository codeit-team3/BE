package com.codeit.sprint.team3.backend.bookclub.application.port.in;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface BookClubUseCase {
    void createBookClub(BookClub bookClub, Long userId);

    List<BookClub> findBookClubsBy(BookClubType bookClubType, MeetingType meetingType, Integer memberLimit, String location, LocalDateTime targetDate, OrderType orderType, Pageable pageable, String searchKeyword, Long userId);

    BookClub getById(Long bookClubId);

    void deleteBookClub(Long bookClubId, Long userId);
}
