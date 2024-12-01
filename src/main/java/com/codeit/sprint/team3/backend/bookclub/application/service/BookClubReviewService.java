package com.codeit.sprint.team3.backend.bookclub.application.service;

import com.codeit.sprint.team3.backend.bookclub.application.port.out.BookClubReviewPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubReviewUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookClubReviewService implements BookClubReviewUseCase {
    private final BookClubReviewPort bookClubReviewPort;

    @Override
    public void saveBookClubReview(Long bookClubId, Long userId, Integer rating, String content) {
        bookClubReviewPort.saveBookClubReview(bookClubId, userId, rating, content);
    }
}
