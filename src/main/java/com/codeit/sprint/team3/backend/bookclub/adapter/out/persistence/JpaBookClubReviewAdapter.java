package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubReviewEntity;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubReviewEntityRepository;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubReviewQueryRepository;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.BookClubReviewPort;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubReview;
import lombok.RequiredArgsConstructor;
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
    public List<BookClubReview> findAllByBookClubId(Long id) {
        return bookClubReviewQueryRepository.findAllByBookClubId(id)
                .stream()
                .map(BookClubReviewEntity::toDomain)
                .toList();
    }
}
