package com.codeit.sprint.team3.backend.bookclub.domain;

import java.util.Map;

public record BookClubReviewCount(Integer one, Integer two, Integer three, Integer four, Integer five) {
    public static BookClubReviewCount of(Map<Integer, Long> ratingToCount) {
        return new BookClubReviewCount(
                ratingToCount.getOrDefault(1, 0L).intValue(),
                ratingToCount.getOrDefault(2, 0L).intValue(),
                ratingToCount.getOrDefault(3, 0L).intValue(),
                ratingToCount.getOrDefault(4, 0L).intValue(),
                ratingToCount.getOrDefault(5, 0L).intValue()
        );
    }
}
