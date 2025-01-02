package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReviewCount;

public record BookClubReviewCountResponse(
        Integer one,
        Integer two,
        Integer three,
        Integer four,
        Integer five
) {
    public static BookClubReviewCountResponse from(BookClubReviewCount bookClubReviewCount) {
        return new BookClubReviewCountResponse(bookClubReviewCount.one(), bookClubReviewCount.two(), bookClubReviewCount.three(), bookClubReviewCount.four(), bookClubReviewCount.five());
    }
}
