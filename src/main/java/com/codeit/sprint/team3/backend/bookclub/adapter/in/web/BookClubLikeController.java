package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubLikeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-clubs/{id}/likes")
public class BookClubLikeController {
    private final BookClubLikeUseCase bookClubLikeUseCase;
    private final UserProfileUseCase userProfileUseCase;

    @PostMapping
    public ResponseEntity<Void> likeBookClub(@PathVariable Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userProfileUseCase.getUserByEmail(email).getId();
        bookClubLikeUseCase.saveBookClubLike(id, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unlikeBookClub(@PathVariable Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userProfileUseCase.getUserByEmail(email).getId();
        bookClubLikeUseCase.deleteBookClubLike(id, userId);
        return ResponseEntity.noContent()
                .build();
    }
}
