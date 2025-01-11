package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.UserProfileUseCase;
import com.codeit.sprint.team3.backend.auth.domain.model.User;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubLikeUseCase;
import com.codeit.sprint.team3.backend.common.annotation.CustomAuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-clubs/{id}/likes")
public class BookClubLikeController {
    private final BookClubLikeUseCase bookClubLikeUseCase;
    private final UserProfileUseCase userProfileUseCase;

    @PostMapping
    public ResponseEntity<Void> likeBookClub(@PathVariable Long id, @CustomAuthenticationPrincipal User user) {
        bookClubLikeUseCase.saveBookClubLike(id, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unlikeBookClub(@PathVariable Long id, @CustomAuthenticationPrincipal User user) {
        bookClubLikeUseCase.deleteBookClubLike(id, user.getId());
        return ResponseEntity.noContent()
                .build();
    }
}
