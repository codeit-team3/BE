package com.codeit.sprint.team3.backend.bookclub.application.service;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.BookClubMemberAlreadyExistsException;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubMemberEntity;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubUseCase;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.CommandBookClubMemberPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubMemberUseCase;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookClubMemberService implements BookClubMemberUseCase {
    private final CommandBookClubMemberPort commandBookClubMemberPort;
    private final BookClubUseCase bookClubUseCase;

    @Override
    @Transactional
    public void joinBookClub(Long bookClubId, Long userId) {
        //존재하는 BookClub인지 확인
        BookClub bookClub = bookClubUseCase.getById(bookClubId);
        //이미 가입한 BookClub인지 확인
        if (commandBookClubMemberPort.existsByBookClubIdAndUserId(bookClubId, userId)) {
            throw new BookClubMemberAlreadyExistsException();
        }
        //BookClub에 가입
        //TODO 동시성 문제가 생길 수 있으므로 Lock 필요. 어떻게 구현할것인가?
        commandBookClubMemberPort.joinBookClub(bookClubId, userId);
        //TODO 채팅방에 멤버 추가
        //TODO 알림
    }

    @Override
    @Transactional
    public void leaveBookClub(Long bookClubId, Long userId) {
        //존재하는 BookClub인지 확인
        BookClub bookClub = bookClubUseCase.getById(bookClubId);
        //BookClub에서 탈퇴
        commandBookClubMemberPort.leaveBookClub(bookClub.getId(), userId);

        /**
         * TODO
         * 1. 남은 유저가 없다면 BookClub 삭제
         * 2. 채팅방에서 멤버 제거
         * 3. 알림
         * 4. 남은 유저가 없다면 채팅방 삭제 및 북클럽 삭제
         */
    }
}
