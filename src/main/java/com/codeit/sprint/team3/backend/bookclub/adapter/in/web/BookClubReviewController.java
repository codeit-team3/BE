package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request.BookClubReviewListOrderType;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request.CreateBookClubReviewRequest;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response.BookClubReviewResponses;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response.ScoredBookClubReviewResponses;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubReviewUseCase;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import com.codeit.sprint.team3.backend.bookclub.domain.ScoredBookClubReview;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book-clubs")
@RequiredArgsConstructor
public class BookClubReviewController {
    private final BookClubReviewUseCase bookClubReviewUseCase;
    private final UserProfileUseCase userProfileUseCase;

    @PostMapping("/{bookClubId}/reviews")
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

    @GetMapping("/{bookClubId}/reviews")
    public ResponseEntity<ScoredBookClubReviewResponses> getBookClubReviewsById(
            @PathVariable Long bookClubId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") String order
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
        ScoredBookClubReview bookClubReviews = bookClubReviewUseCase.getBookClubReviewsById(bookClubId, pageable, BookClubReviewListOrderType.from(order));
        return ResponseEntity.ok(ScoredBookClubReviewResponses.from(bookClubReviews));
    }

    @DeleteMapping("/{bookClubId}/reviews/{reviewId}")
    public ResponseEntity<Void> deleteBookClubReview(@PathVariable Long bookClubId, @PathVariable Long reviewId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userProfileUseCase.getUserByEmail(email).getId();
        bookClubReviewUseCase.deleteBookClubReview(bookClubId, userId, reviewId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my-reviews")
    public ResponseEntity<BookClubReviewResponses> getMyReviews(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") String order
    ) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userProfileUseCase.getUserByEmail(email).getId();
        Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
        List<BookClubReview> bookClubReviews = bookClubReviewUseCase.getUserReviews(userId, pageable, BookClubReviewListOrderType.myBookClubReviewOrderType(order));
        return ResponseEntity.ok(BookClubReviewResponses.from(bookClubReviews));
    }

    @GetMapping("/users/{userId}/reviews")
    public ResponseEntity<BookClubReviewResponses> getUserReviews(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") String order
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
        List<BookClubReview> bookClubReviews = bookClubReviewUseCase.getUserReviews(userId, pageable, BookClubReviewListOrderType.myBookClubReviewOrderType(order));
        return ResponseEntity.ok(BookClubReviewResponses.from(bookClubReviews));
    }
}
