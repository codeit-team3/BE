package com.codeit.sprint.team3.backend.bookclub.application.service;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.BookClubNotExistException;
import com.codeit.sprint.team3.backend.bookclub.application.port.in.BookClubUseCase;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.CommandBookClubMemberPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.CommandBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.QueryBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.domain.*;
import com.codeit.sprint.team3.backend.common.application.port.out.FileUploadPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookClubService implements BookClubUseCase {
    private final CommandBookClubPort commandBookClubPort;
    private final QueryBookClubPort queryBookClubPort;
    private final CommandBookClubMemberPort commandBookClubMemberPort;
    private final FileUploadPort fileUploadPort;

    @Override
    @Transactional
    public void createBookClub(BookClub bookClub, Long userId, MultipartFile file) {
        BookClub savedBookClub = commandBookClubPort.saveBookClub(bookClub, userId, file != null);
        commandBookClubMemberPort.save(BookClubMember.of(savedBookClub.getId(), userId));

        fileUploadPort.uploadImageToS3(file, "bookclubs/" + savedBookClub.getId(), "image.jpg", "jpg");
    }

    @Override
    public List<BookClub> findBookClubsBy(BookClubType bookClubType, MeetingType meetingType, Integer memberLimitMin, Integer memberLimitMax, String location, LocalDateTime targetDate, OrderType orderType, Pageable pageable, String searchKeyword, Long userId) {
        return queryBookClubPort.findBookClubsBy(bookClubType, meetingType, memberLimitMin, memberLimitMax, location, targetDate, orderType, pageable, searchKeyword, userId);
    }

    @Override
    public BookClub getById(Long bookClubId) {
        return queryBookClubPort.findById(bookClubId)
                .orElseThrow(BookClubNotExistException::new);
    }

    @Override
    @Transactional
    public void deleteBookClub(Long bookClubId, Long userId) {
        BookClub bookClub = queryBookClubPort.findById(bookClubId)
                .orElseThrow(BookClubNotExistException::new);
        if (!Objects.equals(bookClub.getCreatedBy(), userId)) {
            throw new BookClubNotExistException();
        }
        commandBookClubPort.deleteBookClub(bookClubId);
    }

    @Override
    public BookClub findBookClubById(Long bookClubId, Long userId) {
        return queryBookClubPort.findBookClubById(bookClubId, userId);
    }

    @Override
    public List<BookClub> findMyCreatedBookClubs(Long userId, OrderType orderType, Pageable pageable, boolean includeInactive) {
        return queryBookClubPort.findMyCreatedBookClubs(userId, orderType, pageable, includeInactive);
    }

    @Override
    public List<BookClub> findUserJoinedBookClubs(Long userId, OrderType orderType, Pageable pageable, boolean includeInactive) {
        return queryBookClubPort.findUserJoinedBookClubs(userId, orderType, pageable, includeInactive);
    }
}
