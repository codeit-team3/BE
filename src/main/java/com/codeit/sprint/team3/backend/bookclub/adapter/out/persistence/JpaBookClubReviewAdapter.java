package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.BookClubReviewNotExistException;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubReviewEntity;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubReviewEntityRepository;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubReviewQueryRepository;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.BookClubReviewPort;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;
import com.codeit.sprint.team3.backend.bookclub.domain.ScoredBookClubReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaBookClubReviewAdapter implements BookClubReviewPort {
    private final BookClubReviewEntityRepository bookClubReviewRepository;
    private final BookClubReviewQueryRepository bookClubReviewQueryRepository;

    @Override
    public void saveBookClubReview(BookClubReview bookClubReview) {
        bookClubReviewRepository.save(BookClubReviewEntity.from(bookClubReview));
    }

    @Override
    public ScoredBookClubReview findAllByBookClubId(Long bookClubId, Pageable pageable, OrderType order) {
        double averageRating = bookClubReviewQueryRepository.getBookClubReviewAverageRating(bookClubId);
        List<BookClubReview> bookClubReviews = bookClubReviewQueryRepository.findAllByBookClubId(bookClubId, pageable, order)
                .stream()
                .map(BookClubReviewEntity::toDomain)
                .toList();
        return ScoredBookClubReview.of(averageRating, bookClubReviews);
    }

    @Override
    public void deleteBookClubReview(Long bookClubId, Long userId, Long bookClubReviewId) {
        BookClubReviewEntity bookClubReviewEntity = bookClubReviewRepository.getByIdAndBookClubIdAndUserIdAndIsInactiveFalse(bookClubId, bookClubReviewId, userId)
                .orElseThrow(BookClubReviewNotExistException::new);
        bookClubReviewEntity.inactivate();
    }

    @Override
    public List<BookClubReview> findMyReviews(Long userId, Pageable pageable, OrderType orderType) {
        return bookClubReviewQueryRepository.findMyReviews(userId, pageable, orderType)
                .stream()
                .map(BookClubReviewEntity::toDomain)
                .toList();
    }
}
