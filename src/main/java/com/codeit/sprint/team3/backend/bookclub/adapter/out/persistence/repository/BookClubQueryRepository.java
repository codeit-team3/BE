package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.IllegalTypeConversionException;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubEntity;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.QBookClubEntity.bookClubEntity;
import static com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.QBookClubLikeEntity.bookClubLikeEntity;
import static com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.QBookClubMemberEntity.bookClubMemberEntity;

@RequiredArgsConstructor
@Repository
public class BookClubQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<BookClubDto> findBookClubsBy(BookClubType bookClubType, MeetingType meetingType, Integer memberLimit, String location, LocalDateTime targetDate, OrderType orderType, Pageable pageable, String searchKeyword, Long userId) {
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isNullOrEmpty(location)) {
            builder.and(bookClubEntity.town.eq(location));
        }
        if (memberLimit != null) {
            builder.and(bookClubEntity.memberLimit.eq(memberLimit));
        }
        if (targetDate != null) {
            LocalDateTime nextDayStart = targetDate.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime currentDayStart = targetDate.withHour(0).withMinute(0).withSecond(0).withNano(0);
            builder.and(bookClubEntity.targetDate.goe(currentDayStart)); // 현재 날짜 시작 시간보다 크거나 같고
            builder.and(bookClubEntity.targetDate.lt(nextDayStart));     // 다음 날 시작 시간보다 작은 조건);
        }
        if (orderType == OrderType.END) {
            builder.and(bookClubEntity.endDate.goe(LocalDateTime.now()));
        }
        if (!StringUtils.isNullOrEmpty(searchKeyword)) {
            builder.and(bookClubEntity.title.contains(searchKeyword)
                    .or(bookClubEntity.town.contains(searchKeyword)));
        }

        return jpaQueryFactory.select(getBookClubDtoProjection())
                .from(bookClubEntity)
                .innerJoin(bookClubMemberEntity).on(bookClubEntity.id.eq(bookClubMemberEntity.bookClubId).and(bookClubMemberEntity.isInactive.eq(false)))
                .leftJoin(bookClubLikeEntity).on(bookClubEntity.id.eq(bookClubLikeEntity.bookClubId).and(bookClubLikeEntity.userId.eq(userId)))
                .where(
                        filterEnum(bookClubType, bookClubEntity.bookClubType),
                        filterEnum(meetingType, bookClubEntity.meetingType),
                        bookClubEntity.isInactive.eq(false),
                        builder
                )
                .groupBy(bookClubEntity.id)
                .orderBy(getOrderSpecifiers(orderType))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private OrderSpecifier<?> getOrderSpecifiers(OrderType orderType) {
        if (orderType == OrderType.DESC) {
            return bookClubEntity.createdAt.desc();
        }
        if (orderType == OrderType.ASC) {
            return bookClubEntity.createdAt.asc();
        }
        if (orderType == OrderType.END) {
            return bookClubEntity.endDate.desc();
        }
        throw new IllegalTypeConversionException(orderType.name());
    }

    private static QBookClubDto getBookClubDtoProjection() {
        return new QBookClubDto(
                bookClubEntity.id,
                bookClubEntity.title,
                bookClubEntity.description,
                bookClubEntity.meetingType,
                bookClubEntity.bookClubType,
                bookClubEntity.targetDate,
                bookClubEntity.endDate,
                bookClubEntity.memberLimit,
                bookClubEntity.town,
                bookClubEntity.createdBy,
                bookClubEntity.createdAt,
                bookClubEntity.isInactive,
                bookClubMemberEntity.count().intValue().as("memberCount"),
                Expressions.booleanTemplate("case when {0} > 0 then true else false end",
                                bookClubLikeEntity.count())
                        .as("isLiked")
        );

    }

    private <T extends Enum<T>> BooleanExpression filterEnum(T enumValue, EnumPath<T> enumPath) {
        if (enumValue.name().equals("ALL")) {
            return null;
        }
        return enumPath.eq(enumValue);
    }

    public BookClubDto findBookClubBy(Long bookClubId, Long userId) {
        return jpaQueryFactory.select(getBookClubDtoProjection())
                .from(bookClubEntity)
                .innerJoin(bookClubMemberEntity).on(bookClubEntity.id.eq(bookClubMemberEntity.bookClubId).and(bookClubMemberEntity.isInactive.eq(false)))
                .leftJoin(bookClubLikeEntity).on(bookClubEntity.id.eq(bookClubLikeEntity.bookClubId).and(bookClubLikeEntity.userId.eq(userId)))
                .where(
                        bookClubEntity.isInactive.eq(false),
                        bookClubEntity.id.eq(bookClubId)
                )
                .groupBy(bookClubEntity.id)
                .fetchOne();
    }

    public List<BookClubEntity> findMyCreatedBookClubs(Long userId, OrderType orderType, Pageable pageable) {
        return jpaQueryFactory.select(bookClubEntity)
                .from(bookClubEntity)
                .leftJoin(bookClubLikeEntity).on(bookClubEntity.id.eq(bookClubLikeEntity.bookClubId).and(bookClubLikeEntity.userId.eq(userId)))
                .where(
                        bookClubEntity.createdBy.eq(userId)
                )
                .groupBy(bookClubEntity.id)
                .orderBy(getOrderSpecifiers(orderType))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public List<BookClubEntity> findMyJoinedBookClubs(Long userId, OrderType orderType, Pageable pageable) {
        return jpaQueryFactory.select(bookClubEntity)
                .from(bookClubEntity)
                .innerJoin(bookClubMemberEntity).on(bookClubEntity.id.eq(bookClubMemberEntity.bookClubId).and(bookClubMemberEntity.userId.eq(userId)).and(bookClubMemberEntity.isInactive.eq(false)))
                .leftJoin(bookClubLikeEntity).on(bookClubEntity.id.eq(bookClubLikeEntity.bookClubId).and(bookClubLikeEntity.userId.eq(userId)))
                .groupBy(bookClubEntity.id)
                .orderBy(getOrderSpecifiers(orderType))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
