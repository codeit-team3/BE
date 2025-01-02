package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence;

import com.codeit.sprint.team3.backend.auth.adapter.out.persistence.UserEntity;
import com.codeit.sprint.team3.backend.auth.adapter.out.persistence.UserRepository;
import com.codeit.sprint.team3.backend.bookclub.adapter.exception.BookClubReviewNotExistException;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubReviewEntity;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubReviewEntityRepository;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubReviewQueryRepository;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.BookClubReviewPort;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReviewCount;
import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;
import com.codeit.sprint.team3.backend.bookclub.domain.ScoredBookClubReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaBookClubReviewAdapter implements BookClubReviewPort {
    private final BookClubReviewEntityRepository bookClubReviewRepository;
    private final BookClubReviewQueryRepository bookClubReviewQueryRepository;
    private final UserRepository userRepository;

    @Override
    public void saveBookClubReview(BookClubReview bookClubReview) {
        bookClubReviewRepository.save(BookClubReviewEntity.from(bookClubReview));
    }

    @Override
    public ScoredBookClubReview findAllByBookClubId(Long bookClubId, Pageable pageable, OrderType order) {
        double averageRating = bookClubReviewQueryRepository.getBookClubReviewAverageRating(bookClubId);
        Map<Integer, Long> ratingToCount = bookClubReviewQueryRepository.getBookClubReviewsByBookClubId(bookClubId)
                .stream()
                .collect(Collectors.groupingBy(BookClubReviewEntity::getRating, Collectors.counting()));
        List<BookClubReviewEntity> bookClubReviewEntities = bookClubReviewQueryRepository.findAllByBookClubId(bookClubId, pageable, order);
        Map<Long, UserEntity> userIdToUserEntity = userRepository.findAllById(extractUserIds(bookClubReviewEntities))
                .stream()
                .collect(Collectors.toMap(UserEntity::getId, user -> user));
        List<BookClubReview> bookClubReviews = bookClubReviewEntities.stream()
                .map(bookClubReviewEntity -> bookClubReviewEntity.toDomain(userIdToUserEntity.get(bookClubReviewEntity.getUserId())))
                .toList();
        return ScoredBookClubReview.of(averageRating, bookClubReviews, BookClubReviewCount.of(ratingToCount));
    }

    @Override
    public void deleteBookClubReview(Long bookClubId, Long userId, Long bookClubReviewId) {
        BookClubReviewEntity bookClubReviewEntity = bookClubReviewRepository.getByIdAndUserIdAndIsInactiveFalse(bookClubReviewId, userId)
                .orElseThrow(BookClubReviewNotExistException::new);
        bookClubReviewEntity.inactivate();
    }

    @Override
    public List<BookClubReview> findUserReviews(Long userId, Pageable pageable, OrderType orderType, boolean includeInactive) {
        List<BookClubReviewEntity> bookClubReviewEntities = bookClubReviewQueryRepository.findUserReviews(userId, pageable, orderType, includeInactive);
        Map<Long, UserEntity> userIdToUserEntity = userRepository.findAllById(extractUserIds(bookClubReviewEntities))
                .stream()
                .collect(Collectors.toMap(UserEntity::getId, user -> user));
        return bookClubReviewEntities.stream()
                .map(bookClubReviewEntity -> bookClubReviewEntity.toDomain(userIdToUserEntity.get(bookClubReviewEntity.getUserId())))
                .toList();
    }

    private static List<Long> extractUserIds(List<BookClubReviewEntity> bookClubReviewEntities) {
        return bookClubReviewEntities.stream()
                .map(BookClubReviewEntity::getUserId)
                .toList();
    }
}
