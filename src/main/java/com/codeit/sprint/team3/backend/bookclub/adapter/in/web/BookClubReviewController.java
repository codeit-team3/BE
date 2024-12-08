package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request.CreateBookClubReviewRequest;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response.BookClubReviewResponses;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubReviewUseCase;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book-clubs/{bookClubId}/reviews")
@RequiredArgsConstructor
public class BookClubReviewController {
    private final BookClubReviewUseCase bookClubReviewUseCase;

    @PostMapping
    public ResponseEntity<Void> createBookClubReview(
            @PathVariable Long bookClubId,
            @Valid @RequestBody CreateBookClubReviewRequest createBookClubReviewRequest
    ) {
        //TODO 로그인 여부 확인 및 데이터 가져오기
        Long userId = 1L;
        bookClubReviewUseCase.saveBookClubReview(bookClubId, userId, createBookClubReviewRequest.rating(), createBookClubReviewRequest.content());
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping
    public ResponseEntity<BookClubReviewResponses> getBookClubReviewsById(@PathVariable Long bookClubId) {
        List<BookClubReview> bookClubReviews = bookClubReviewUseCase.getBookClubReviewsById(bookClubId);
        return ResponseEntity.ok(BookClubReviewResponses.from(bookClubReviews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookClubReview(@PathVariable Long bookClubId, @PathVariable Long id) {
        //TODO 로그인 여부 확인 및 데이터 가져오기
        Long userId = 1L;
        bookClubReviewUseCase.deleteBookClubReview(bookClubId, userId, id);
        return ResponseEntity.noContent().build();
    }
}
