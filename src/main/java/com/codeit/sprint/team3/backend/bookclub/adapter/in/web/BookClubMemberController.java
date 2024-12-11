package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-clubs/{id}")
public class BookClubMemberController {
    private final BookClubMemberUseCase bookClubMemberUseCase;

    @PostMapping("/join")
    public ResponseEntity<Void> joinBookClub(@PathVariable Long id) {
        //TODO 로그인 여부 확인 및 데이터 가져오기
        Long userId = 1L;
        bookClubMemberUseCase.joinBookClub(id, userId);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/leave")
    public ResponseEntity<Void> leaveBookClub(@PathVariable Long id) {
        //TODO 로그인 여부 확인 및 데이터 가져오기
        Long userId = 1L;
        bookClubMemberUseCase.leaveBookClub(id, userId);
        return ResponseEntity.noContent()
                .build();
    }
}
