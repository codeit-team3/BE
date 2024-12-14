package com.codeit.sprint.team3.backend.bookclub.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoredBookClubReview {
    private final Double score;
    private final List<BookClubReview> bookClubReviews;

    public static ScoredBookClubReview of(Double score, List<BookClubReview> bookClubReviews) {
        return new ScoredBookClubReview(score, bookClubReviews);
    }
}
