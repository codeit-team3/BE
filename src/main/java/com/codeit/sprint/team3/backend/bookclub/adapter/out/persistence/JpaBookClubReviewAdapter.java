package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence;

import com.codeit.sprint.team3.backend.auth.adapter.out.persistence.UserRepository;
import com.codeit.sprint.team3.backend.bookclub.adapter.exception.BookClubReviewNotExistException;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubReviewEntity;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubReviewDto;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubReviewEntityRepository;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubReviewQueryRepository;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.BookClubReviewPort;
import com.codeit.sprint.team3.backend.bookclub.domain.*;
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
    private final ImageFactory imageFactory;

    @Override
    public void saveBookClubReview(BookClubReview bookClubReview) {
        bookClubReviewRepository.save(BookClubReviewEntity.from(bookClubReview));
    }

    @Override
    public ScoredBookClubReview findAllByBookClubId(Long bookClubId, Pageable pageable, OrderType order) {
        Map<Integer, Long> ratingToCount = bookClubReviewQueryRepository.getBookClubReviewsByBookClubId(bookClubId)
                .stream()
                .collect(Collectors.groupingBy(BookClubReviewEntity::getRating, Collectors.counting()));
        double averageRating = ratingToCount.entrySet().stream()
                .mapToDouble(entry -> entry.getKey() * entry.getValue()) // 평점 * 갯수
                .sum() // 곱한 값들의 총합
                / ratingToCount.values().stream().mapToLong(count -> count).sum();

        List<BookClubReview> bookClubReviews = bookClubReviewQueryRepository.findAllByBookClubId(bookClubId, pageable, order)
                .stream()
                .map(bookClubReviewDto -> bookClubReviewDto.toModel(imageFactory.createImageUrl("bookclubs", bookClubReviewDto.bookClubId(), "image.jpg", bookClubReviewDto.hasImage())))
                .toList();
        return ScoredBookClubReview.of(Double.parseDouble(String.format("%.1f", averageRating)), bookClubReviews, BookClubReviewCount.of(ratingToCount));
    }

    @Override
    public void deleteBookClubReview(Long bookClubId, Long userId, Long bookClubReviewId) {
        BookClubReviewEntity bookClubReviewEntity = bookClubReviewRepository.getByIdAndUserIdAndIsInactiveFalse(bookClubReviewId, userId)
                .orElseThrow(BookClubReviewNotExistException::new);
        bookClubReviewEntity.inactivate();
    }

    @Override
    public List<BookClubReview> findUserReviews(Long userId, Pageable pageable, OrderType orderType, boolean includeInactive) {
        List<BookClubReviewDto> bookClubReviewDtos = bookClubReviewQueryRepository.findUserReviews(userId, pageable, orderType, includeInactive);
        return bookClubReviewDtos.stream()
                .map(bookClubReviewDto -> bookClubReviewDto.toModel(imageFactory.createImageUrl("bookclubs", bookClubReviewDto.bookClubId(), "image.jpg", bookClubReviewDto.hasImage())))
                .toList();
    }

    private static List<Long> extractUserIds(List<BookClubReviewEntity> bookClubReviewEntities) {
        return bookClubReviewEntities.stream()
                .map(BookClubReviewEntity::getUserId)
                .toList();
    }
}
