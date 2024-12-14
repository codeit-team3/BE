package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response;

import com.codeit.sprint.team3.backend.bookclub.domain.ScoredBookClubReview;

import java.util.List;

public record ScoredBookClubReviewResponses(Double averageScore, List<BookClubReviewResponse> reviews) {
    public static ScoredBookClubReviewResponses from(ScoredBookClubReview scoredBookClubReview) {
        List<BookClubReviewResponse> bookClubReviewResponses = scoredBookClubReview.getBookClubReviews()
                .stream()
                .map(BookClubReviewResponse::from)
                .toList();
        return new ScoredBookClubReviewResponses(scoredBookClubReview.getScore(), bookClubReviewResponses);
    }
}
