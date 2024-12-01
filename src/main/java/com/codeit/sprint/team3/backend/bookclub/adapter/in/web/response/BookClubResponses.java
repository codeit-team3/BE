package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response;

import com.codeit.sprint.team3.backend.bookclub.domain.model.BookClub;

import java.util.List;

public record BookClubResponses(List<BookClubResponse> bookClubs) {
    public static BookClubResponses from(List<BookClub> bookClubs) {
        List<BookClubResponse> list = bookClubs.stream()
                .map(BookClubResponse::from)
                .toList();
        return new BookClubResponses(list);
    }
}
