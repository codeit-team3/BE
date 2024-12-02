package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response.BookClubReviewResponses;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubReviewUseCase;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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
            @Valid
            @Max(message = "별점은 최대 10점 입니다.", value = 10)
            @PositiveOrZero(message = "별점은 0점 이상이어야 합니다.")
            Integer rating,
            @Valid @NotBlank String content
    ) {
        //TODO 로그인 여부 확인 및 데이터 가져오기
        Long userId = 1L;
        bookClubReviewUseCase.saveBookClubReview(bookClubId, userId, rating, content);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping
    public ResponseEntity<BookClubReviewResponses> getBookClubReviewsById(@PathVariable Long bookClubId) {
        //TODO 로그인 여부 확인 및 데이터 가져오기
        Long userId = 1L;
        List<BookClubReview> bookClubReviews = bookClubReviewUseCase.getBookClubReviewsById(bookClubId);
        return ResponseEntity.ok(null);
    }
}
