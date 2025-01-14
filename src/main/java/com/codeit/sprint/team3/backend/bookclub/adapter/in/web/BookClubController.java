package com.codeit.sprint.team3.backend.bookclub.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.domain.model.User;
import com.codeit.sprint.team3.backend.bookclub.adapter.exception.InvalidRequest;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request.BookClubListOrderType;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request.CreateBookClubRequest;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response.ExpandedBookClubResponse;
import com.codeit.sprint.team3.backend.bookclub.adapter.in.web.response.ExpandedBookClubResponses;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubUseCase;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.codeit.sprint.team3.backend.common.annotation.CustomAuthenticationPrincipal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private static final List<String> VALID_EXTENSIONS = List.of("jpg", "jpeg");

    private final BookClubUseCase bookClubUseCase;

    @PostMapping
    public ResponseEntity<Void> createBookClub(
            @RequestPart(required = false) MultipartFile image,
            @RequestPart(name = "bookClub") @Valid CreateBookClubRequest createBookClubRequest,
            @CustomAuthenticationPrincipal User user
    ) {
        validateImage(image);
        bookClubUseCase.createBookClub(createBookClubRequest.toDomain(), user.getId(), image);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    private void validateImage(MultipartFile image) {
        if (image == null) {
            return;
        }
        if (!VALID_EXTENSIONS.contains(StringUtils.getFilenameExtension(image.getOriginalFilename()))) {
            throw new InvalidRequest("image", String.format("이미지는 %s 형식이어야 합니다.", String.join(", ", VALID_EXTENSIONS)));
        }
        long size = image.getSize();
        if (size > 1024 * 1024 * 10) {
            throw new InvalidRequest("image", "이미지 크기는 10MB 이하여야 합니다.");
        }
    }

    @GetMapping
    public ResponseEntity<ExpandedBookClubResponses> findBookClubs(
            @RequestParam(defaultValue = "ALL") String bookClubType,
            @RequestParam(defaultValue = "ALL") String meetingType,
            @RequestParam(defaultValue = "DESC") String order,
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "false") Boolean isAvailable,
            String searchKeyword,
            Integer memberLimitMin,
            Integer memberLimitMax,
            String location, //동 단위 town
            LocalDateTime targetDate,
            @CustomAuthenticationPrincipal(required = false) User user
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
        List<BookClub> bookClubs = bookClubUseCase.findBookClubsBy(BookClubType.getQueryType(bookClubType), MeetingType.getQueryType(meetingType), memberLimitMin, memberLimitMax, location, targetDate, BookClubListOrderType.from(order), pageable, searchKeyword, user.getId(), isAvailable);
        return ResponseEntity.ok()
                .body(ExpandedBookClubResponses.from(bookClubs));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookClub(@PathVariable Long id, @CustomAuthenticationPrincipal User user) {
        bookClubUseCase.deleteBookClub(id, user.getId());
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping("/{bookClubId}")
    public ResponseEntity<ExpandedBookClubResponse> findBookClub(@PathVariable Long bookClubId, @CustomAuthenticationPrincipal(required = false) User user) {
        BookClub bookClub = bookClubUseCase.findBookClubById(bookClubId, user.getId());
        return ResponseEntity.ok()
                .body(ExpandedBookClubResponse.from(bookClub));
    }

    @GetMapping("/my-created")
    public ResponseEntity<ExpandedBookClubResponses> findMyCreatedBookClubs(
            @RequestParam(defaultValue = "DESC") String order,
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @CustomAuthenticationPrincipal User user
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
        List<BookClub> bookClubs = bookClubUseCase.findMyCreatedBookClubs(user.getId(), user.getId(), BookClubListOrderType.myBookClubOrderType(order), pageable, true);
        return ResponseEntity.ok()
                .body(ExpandedBookClubResponses.from(bookClubs));
    }

    @GetMapping("/my-joined")
    public ResponseEntity<ExpandedBookClubResponses> findMyJoinedBookClubs(
            @RequestParam(defaultValue = "DESC") String order,
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @CustomAuthenticationPrincipal User user
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
        List<BookClub> bookClubs = bookClubUseCase.findUserJoinedBookClubs(user.getId(), user.getId(), BookClubListOrderType.myBookClubOrderType(order), pageable, true);
        return ResponseEntity.ok()
                .body(ExpandedBookClubResponses.from(bookClubs));
    }

    @GetMapping("/users/{userId}/created")
    public ResponseEntity<ExpandedBookClubResponses> findUserCreatedBookClubs(
            @PathVariable(name = "userId") Long targetUserId,
            @RequestParam(defaultValue = "DESC") String order,
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @CustomAuthenticationPrincipal(required = false) User user
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
        List<BookClub> bookClubs = bookClubUseCase.findMyCreatedBookClubs(user.getId(), targetUserId, BookClubListOrderType.myBookClubOrderType(order), pageable, false);
        return ResponseEntity.ok()
                .body(ExpandedBookClubResponses.from(bookClubs));
    }

    @GetMapping("/users/{userId}/joined")
    public ResponseEntity<ExpandedBookClubResponses> findUserJoinedBookClubs(
            @PathVariable(name = "userId") Long targetUserId,
            @RequestParam(defaultValue = "DESC") String order,
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @CustomAuthenticationPrincipal(required = false) User user
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
        List<BookClub> bookClubs = bookClubUseCase.findUserJoinedBookClubs(user.getId(), targetUserId, BookClubListOrderType.myBookClubOrderType(order), pageable, false);
        return ResponseEntity.ok()
                .body(ExpandedBookClubResponses.from(bookClubs));
    }
}
