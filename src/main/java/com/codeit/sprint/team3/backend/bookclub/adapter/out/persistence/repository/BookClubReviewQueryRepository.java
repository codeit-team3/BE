package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.IllegalTypeConversionException;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubReviewEntity;
import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.QBookClubReviewEntity.bookClubReviewEntity;

@Repository
@RequiredArgsConstructor
public class BookClubReviewQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<BookClubReviewEntity> findAllByBookClubId(Long id, Pageable pageable, OrderType order) {
        return jpaQueryFactory.selectFrom(bookClubReviewEntity)
                .where(
                        bookClubReviewEntity.bookClubId.eq(id),
                        bookClubReviewEntity.isInactive.isFalse()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getDesc(order))
                .fetch();
    }

    private static OrderSpecifier<?> getDesc(OrderType order) {
        return switch (order) {
            case DESC -> bookClubReviewEntity.createdAt.desc();
            case RATE_ASC -> bookClubReviewEntity.rating.asc();
            case RATE_DESC -> bookClubReviewEntity.rating.desc();
            default -> throw new IllegalTypeConversionException(order.name());
        };
    }
}
