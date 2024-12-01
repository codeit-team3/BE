package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubReviewUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book-clubs/{bookClubId}/reviews")
@RequiredArgsConstructor
public class BookClubReviewController {
    private final BookClubReviewUseCase bookClubReviewUseCase;

    @PostMapping
    public ResponseEntity<Void> createBookClubReview(
            @PathVariable Long bookClubId,
            @Valid @Max(10) @PositiveOrZero Integer rating,
            @Valid @NotBlank String content
    ) {
        //TODO 로그인 여부 확인 및 데이터 가져오기
        Long userId = 1L;
        bookClubReviewUseCase.saveBookClubReview(bookClubId, userId, rating, content);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
