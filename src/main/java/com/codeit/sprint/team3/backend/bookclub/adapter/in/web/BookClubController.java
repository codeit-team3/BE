package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.InvalidRequest;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request.BookClubListOrderType;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request.CreateBookClubRequest;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response.BookClubResponses;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubUseCase;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book-clubs")
@RequiredArgsConstructor
public class BookClubController {
    private final BookClubUseCase bookClubUseCase;

    @SneakyThrows
    @PostMapping
    public ResponseEntity<Void> createBookClub(
            @RequestPart MultipartFile image,
            @RequestPart(name = "bookClub") @Valid CreateBookClubRequest createBookClubRequest
    ) {
        validateImage(image);
        //TODO 이미지 저장
        //TODO 유저 security context에서 아이디 가져오기
        Long userId = 1L;
        bookClubUseCase.createBookClub(createBookClubRequest.toDomain(), userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    private void validateImage(MultipartFile image) {
        if (image.isEmpty()) {
            return;
        }
        //TODO 이미지 형식 제한 추가하기~
        if (!"jpg".equals(StringUtils.getFilenameExtension(image.getOriginalFilename()))) {
            throw new InvalidRequest("image", "이미지는 jpg 형식이어야 합니다.");
        }
        long size = image.getSize();
        if (size > 1024 * 1024 * 10) {
            throw new InvalidRequest("image", "이미지 크기는 10MB 이하여야 합니다.");
        }
    }

    @GetMapping
    public ResponseEntity<BookClubResponses> findBookClubs(
            @RequestParam(defaultValue = "ALL") String bookClubType,
            @RequestParam(defaultValue = "ALL") String meetingType,
            @RequestParam(defaultValue = "DESC") String order,
            Integer memberLimit,
            String location, //동 단위 town
            LocalDateTime targetDate
    ) {
        List<BookClub> bookClubs = bookClubUseCase.findBookClubsBy(BookClubType.getQueryType(bookClubType), MeetingType.getQueryType(meetingType), memberLimit, location, targetDate, BookClubListOrderType.from(order));
        return ResponseEntity.ok()
                .body(BookClubResponses.from(bookClubs));
    }
}
