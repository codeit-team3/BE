package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubLikeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-clubs/{id}/likes")
public class BookClubLikeController {
    private final BookClubLikeUseCase bookClubLikeUseCase;

    @PostMapping
    public ResponseEntity<Void> likeBookClub(@PathVariable Long id) {
        //TODO 로그인 여부 확인 및 데이터 가져오기
        Long userId = 1L;
        bookClubLikeUseCase.saveBookClubLike(id, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unlikeBookClub(@PathVariable Long id) {
        //TODO 로그인 여부 확인 및 데이터 가져오기
        Long userId = 1L;
        bookClubLikeUseCase.deleteBookClubLike(id, userId);
        return ResponseEntity.noContent()
                .build();
    }
}
