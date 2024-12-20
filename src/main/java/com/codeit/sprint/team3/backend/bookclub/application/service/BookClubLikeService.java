package com.codeit.sprint.team3.backend.bookclub.application.service;

import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubLikeUseCase;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubUseCase;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.BookClubLikePort;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookClubLikeService implements BookClubLikeUseCase {
    private final BookClubUseCase bookClubUseCase;
    private final BookClubLikePort bookClubLikePort;

    @Override
    @Transactional
    public void saveBookClubLike(Long bookClubId, Long userId) {
        BookClub bookClub = bookClubUseCase.getById(bookClubId);
        bookClubLikePort.saveBookClubLike(BookClubLike.of(bookClub.getId(), userId));
    }

    @Override
    @Transactional
    public void deleteBookClubLike(Long bookClubId, Long userId) {
        BookClub bookClub = bookClubUseCase.getById(bookClubId);
        bookClubLikePort.deleteBookClubLike(BookClubLike.of(bookClub.getId(), userId));
    }
}
