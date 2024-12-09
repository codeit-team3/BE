package com.codeit.sprint.team3.backend.bookclub.application.service;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.BookClubNotExistException;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubUseCase;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.CommandBookClubMemberPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.CommandBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.QueryBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookClubService implements BookClubUseCase {
    private final CommandBookClubPort commandBookClubPort;
    private final QueryBookClubPort queryBookClubPort;
    private final CommandBookClubMemberPort commandBookClubMemberPort;

    @Override
    @Transactional
    public void createBookClub(BookClub bookClub, Long userId) {
        //book club creation logic
        BookClub savedBookClub = commandBookClubPort.saveBookClub(bookClub, userId);

        //save the creator as a member
        commandBookClubMemberPort.save(BookClubMember.of(savedBookClub.getId(), userId));

        /**
         * TODO 채팅 구현되면 아래 로직 추가하기
         * 채팅방 생성
         * 채팅방에 멤버 추가(북클럽 생성자)
         * 알림
         */
    }

    @Override
    public List<BookClub> findBookClubsBy(BookClubType bookClubType, MeetingType meetingType, Integer memberLimit, String location, LocalDateTime targetDate, OrderType orderType) {
        return queryBookClubPort.findBookClubsBy(bookClubType, meetingType, memberLimit, location, targetDate, orderType);
    }

    @Override
    public BookClub getById(Long bookClubId) {
        return queryBookClubPort.findById(bookClubId)
                .orElseThrow(BookClubNotExistException::new);
    }
}
