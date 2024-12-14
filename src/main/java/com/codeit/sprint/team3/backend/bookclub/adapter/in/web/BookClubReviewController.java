package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request.BookClubReviewListOrderType;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request.CreateBookClubReviewRequest;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response.BookClubReviewResponses;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubReviewUseCase;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book-clubs/{bookClubId}/reviews")
@RequiredArgsConstructor
public class BookClubReviewController {
    private final BookClubReviewUseCase bookClubReviewUseCase;
    private final UserProfileUseCase userProfileUseCase;

    @PostMapping
    public ResponseEntity<Void> createBookClubReview(
            @PathVariable Long bookClubId,
            @Valid @RequestBody CreateBookClubReviewRequest createBookClubReviewRequest
    ) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userProfileUseCase.getUserByEmail(email).getId();
        bookClubReviewUseCase.saveBookClubReview(bookClubId, userId, createBookClubReviewRequest.rating(), createBookClubReviewRequest.content());
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping
    public ResponseEntity<BookClubReviewResponses> getBookClubReviewsById(
            @PathVariable Long bookClubId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") String order
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
        List<BookClubReview> bookClubReviews = bookClubReviewUseCase.getBookClubReviewsById(bookClubId, pageable, BookClubReviewListOrderType.from(order));
        return ResponseEntity.ok(BookClubReviewResponses.from(bookClubReviews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookClubReview(@PathVariable Long bookClubId, @PathVariable Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userProfileUseCase.getUserByEmail(email).getId();
        bookClubReviewUseCase.deleteBookClubReview(bookClubId, userId, id);
        return ResponseEntity.noContent().build();
    }
}
