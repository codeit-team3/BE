package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.domain.model.User;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubMemberUseCase;
import com.codeit.sprint.team3.backend.common.annotation.CustomAuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-clubs/{id}")
public class BookClubMemberController {
    private final BookClubMemberUseCase bookClubMemberUseCase;

    @PostMapping("/join")
    public ResponseEntity<Void> joinBookClub(@PathVariable Long id, @CustomAuthenticationPrincipal User user) {
        bookClubMemberUseCase.joinBookClub(id, user.getId());
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/leave")
    public ResponseEntity<Void> leaveBookClub(@PathVariable Long id, @CustomAuthenticationPrincipal User user) {
        bookClubMemberUseCase.leaveBookClub(id, user.getId());
        return ResponseEntity.noContent()
                .build();
    }
}
