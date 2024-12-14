package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;

import java.util.List;

public record BookClubReviewResponses(List<BookClubReviewResponse> reviews) {
    public static BookClubReviewResponses from(List<BookClubReview> reviews) {
        List<BookClubReviewResponse> bookClubReviewResponses = reviews
                .stream()
                .map(BookClubReviewResponse::from)
                .toList();
        return new BookClubReviewResponses(bookClubReviewResponses);
    }
}
