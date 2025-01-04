package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.BookClubNotExistException;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubEntity;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.*;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.CommandBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.QueryBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaBookClubAdapter implements CommandBookClubPort, QueryBookClubPort {
    private final BookClubEntityRepository bookClubEntityRepository;
    private final BookClubQueryRepository bookClubQueryRepository;
    private final BookClubReviewQueryRepository bookClubReviewQueryRepository;
    private final BookClubLikeEntityRepository bookClubLikeEntityRepository;
    private final ImageFactory imageFactory;

    @Override
    public BookClub saveBookClub(BookClub bookClub, Long userId, Boolean hasImage) {
        return bookClubEntityRepository.save(BookClubEntity.of(bookClub, userId, hasImage))
                .toModel();
    }

    @Override
    public void deleteBookClub(Long bookClubId) {
        bookClubEntityRepository.findById(bookClubId)
                .ifPresent(BookClubEntity::delete);
    }

    @Override
    public List<BookClub> findBookClubsBy(BookClubType bookClubType, MeetingType meetingType, Integer memberLimitMin, Integer memberLimitMax, String location, LocalDateTime targetDate, OrderType orderType, Pageable pageable, String searchKeyword, Long userId) {
        List<BookClubDto> bookClubDtos = bookClubQueryRepository.findBookClubsBy(bookClubType, meetingType, memberLimitMin, memberLimitMax, location, targetDate, orderType, pageable, searchKeyword, userId);
        List<Long> bookClubIds = bookClubDtos.stream()
                .map(BookClubDto::getId)
                .toList();
        Map<Long, Double> bookClubIdToRating = bookClubReviewQueryRepository.getBookClubReviewAverageRatingByBookClubIds(bookClubIds);
        return bookClubDtos.stream()
                .map(bookClubDto -> bookClubDto.toModel(imageFactory.createImageUrl("bookclubs", bookClubDto.getId(), "image.jpg", bookClubDto.getHasImage()), bookClubIdToRating.getOrDefault(bookClubDto.getId(), 0.)))
                .toList();
    }

    @Override
    public Optional<BookClub> findById(Long bookClubId) {
        return bookClubEntityRepository.findById(bookClubId)
                .map(BookClubEntity::toModel);
    }

    @Override
    public BookClub findBookClubById(Long bookClubId, Long userId) {
        BookClubDto bookClubDto = bookClubQueryRepository.findBookClubBy(bookClubId, userId);
        if (bookClubDto == null) {
            throw new BookClubNotExistException();
        }
        double averageRating = bookClubReviewQueryRepository.getBookClubReviewAverageRating(bookClubId);
        return bookClubDto.toModel(imageFactory.createImageUrl("bookclubs", bookClubId, "image.jpg", bookClubDto.getHasImage()), averageRating);
    }

    @Override
    public List<BookClub> findMyCreatedBookClubs(Long userId, Long targetUserId, OrderType orderType, Pageable pageable, boolean includeInactive) {
        List<BookClubDto> bookClubDtos = bookClubQueryRepository.findMyCreatedBookClubs(userId, targetUserId, orderType, pageable, includeInactive);
        List<Long> bookClubIds = bookClubDtos.stream()
                .map(BookClubDto::getId)
                .toList();
        Map<Long, Double> bookClubIdToRating = bookClubReviewQueryRepository.getBookClubReviewAverageRatingByBookClubIds(bookClubIds);
        return bookClubDtos.stream()
                .map(bookClubEntity -> bookClubEntity.toModel(imageFactory.createImageUrl("bookclubs", bookClubEntity.getId(), "image.jpg", bookClubEntity.getHasImage()), bookClubIdToRating.getOrDefault(bookClubEntity.getId(), 0.)))
                .toList();
    }

    @Override
    public List<BookClub> findUserJoinedBookClubs(Long userId, Long targetUserId, OrderType orderType, Pageable pageable, boolean includeInactive) {
        List<BookClubDto> bookClubDtos = bookClubQueryRepository.findUserJoinedBookClubs(userId, targetUserId, orderType, pageable, includeInactive);
        List<Long> bookClubIds = bookClubDtos.stream()
                .map(BookClubDto::getId)
                .toList();
        Map<Long, Double> bookClubIdToRating = bookClubReviewQueryRepository.getBookClubReviewAverageRatingByBookClubIds(bookClubIds);
        return bookClubDtos.stream()
                .map(bookClubEntity -> bookClubEntity.toModel(imageFactory.createImageUrl("bookclubs", bookClubEntity.getId(), "image.jpg", bookClubEntity.getHasImage()), bookClubIdToRating.getOrDefault(bookClubEntity.getId(), 0.)))
                .toList();
    }
}
