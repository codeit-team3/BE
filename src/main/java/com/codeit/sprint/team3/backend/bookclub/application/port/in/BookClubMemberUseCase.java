package com.codeit.sprint.team3.backend.bookclub.application.port.in;

import com.codeit.sprint.team3.backend.bookclub.domain.model.Member;

public interface BookClubMemberUseCase {
    void saveMember(Member member);
}
