package com.codeit.sprint.team3.backend.bookclub.application.service;

import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubUseCase;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.BookClubReviewPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubReviewUseCase;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookClubReviewService implements BookClubReviewUseCase {
    private final BookClubUseCase bookClubUseCase;
    private final BookClubReviewPort bookClubReviewPort;

    @Override
    @Transactional
    public void saveBookClubReview(Long bookClubId, Long userId, Integer rating, String content) {
        BookClub bookClub = bookClubUseCase.getById(bookClubId);
        BookClubReview bookClubReview = BookClubReview.of(bookClub.getId(), userId, rating, content);
        bookClubReviewPort.saveBookClubReview(bookClubReview);
    }

    @Override
    public List<BookClubReview> getBookClubReviewsById(Long bookClubId) {
        BookClub bookClub = bookClubUseCase.getById(bookClubId);
        return bookClubReviewPort.findAllByBookClubId(bookClub.getId());
    }

    @Override
    @Transactional
    public void deleteBookClubReview(Long bookClubId, Long userId, Long bookClubReviewId) {
        BookClub bookClub = bookClubUseCase.getById(bookClubId);
        bookClubReviewPort.deleteBookClubReview(bookClub.getId(), userId, bookClubReviewId);
    }
}
