package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-clubs/{id}")
public class BookClubMemberController {
    private final BookClubMemberUseCase bookClubMemberUseCase;
    private final UserProfileUseCase userProfileUseCase;

    @PostMapping("/join")
    public ResponseEntity<Void> joinBookClub(@PathVariable Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userProfileUseCase.getUserByEmail(email).getId();
        bookClubMemberUseCase.joinBookClub(id, userId);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/leave")
    public ResponseEntity<Void> leaveBookClub(@PathVariable Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userProfileUseCase.getUserByEmail(email).getId();
        bookClubMemberUseCase.leaveBookClub(id, userId);
        return ResponseEntity.noContent()
                .build();
    }
}
