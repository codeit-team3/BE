package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.InvalidRequest;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request.CreateBookClubRequest;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/book-club")
@RequiredArgsConstructor
public class BookClubController {
    private final BookClubUseCase bookClubUseCase;

    @SneakyThrows
    @RequestMapping
    public ResponseEntity<Void> createBookClub(
            @RequestPart MultipartFile image,
            @RequestPart(name = "data") @Valid CreateBookClubRequest createBookClubRequest
    ) {
        validateImage(image);
        //TODO 이미지 저장
        bookClubUseCase.createBookClub(createBookClubRequest.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    private void validateImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new InvalidRequest("image", "이미지는 필수입니다.");
        }
        //TODO 이미지 형식 제한
        long size = image.getSize();
        if (size > 1024 * 1024 * 10) {
            throw new InvalidRequest("image", "이미지 크기는 10MB 이하여야 합니다.");
        }
    }
}
