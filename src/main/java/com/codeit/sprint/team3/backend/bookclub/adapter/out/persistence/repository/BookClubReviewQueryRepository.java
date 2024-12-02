package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubReviewEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.QBookClubReviewEntity.bookClubReviewEntity;

@Repository
@RequiredArgsConstructor
public class BookClubReviewQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<BookClubReviewEntity> findAllByBookClubId(Long id) {
        return jpaQueryFactory.selectFrom(bookClubReviewEntity)
                .where(
                        bookClubReviewEntity.bookClubId.eq(id),
                        bookClubReviewEntity.isInactive.isFalse()
                )
                .fetch();
    }
}
