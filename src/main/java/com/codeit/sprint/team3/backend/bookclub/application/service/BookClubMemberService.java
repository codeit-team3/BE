package com.codeit.sprint.team3.backend.bookclub.application.service;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubMemberEntity;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.CommandBookClubMemberPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubMemberUseCase;
import com.codeit.sprint.team3.backend.bookclub.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookClubMemberService implements BookClubMemberUseCase {
    private final CommandBookClubMemberPort commandBookClubMemberPort;

    @Override
    public void saveMember(Member member) {
        commandBookClubMemberPort.save(BookClubMemberEntity.from(member));
    }
}
