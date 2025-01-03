package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;

import java.util.List;

public record ExpandedBookClubResponses(List<ExpandedBookClubResponse> bookClubs) {
    public static ExpandedBookClubResponses from(List<BookClub> bookClubs) {
        List<ExpandedBookClubResponse> expandedBookClubResponses = bookClubs.stream()
                .map(ExpandedBookClubResponse::from)
                .toList();

        return new ExpandedBookClubResponses(expandedBookClubResponses);
    }
}
