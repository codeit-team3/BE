package com.codeit.sprint.team3.backend.bookclub.application.service;

import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubMemberUseCase;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubUseCase;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.CommandBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.QueryBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.codeit.sprint.team3.backend.bookclub.domain.model.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookClubService implements BookClubUseCase {
    private final CommandBookClubPort commandBookClubPort;
    private final QueryBookClubPort queryBookClubPort;
    private final BookClubMemberUseCase bookClubMemberUseCase;

    @Override
    public void createBookClub(BookClub bookClub) {
        //TODO 유저 security context에서 아이디 가져오기
        Long userId = 1L;

        //book club creation logic
        BookClub savedBookClub = commandBookClubPort.saveBookClub(bookClub);

        Member member = Member.of(savedBookClub.getId(), userId);
        bookClubMemberUseCase.saveMember(member);

        /**
         * TODO 채팅 구현되면 아래 로직 추가하기
         * 채팅방 생성
         * 채팅방에 멤버 추가(북클럽 생성자)
         * 알림
         */
    }

    @Override
    public List<BookClub> findBookClubsBy(BookClubType bookClubType, MeetingType meetingType, Integer memberLimit, String location, LocalDate targetDate) {
        return queryBookClubPort.findBookClubsBy(bookClubType, meetingType, memberLimit, location, targetDate);
    }
}
