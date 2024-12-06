package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.QBookClubEntity.bookClubEntity;
import static com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.QBookClubMemberEntity.bookClubMemberEntity;

@RequiredArgsConstructor
@Repository
public class BookClubQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<BookClubDto> findBookClubsBy(BookClubType bookClubType, MeetingType meetingType, Integer memberLimit, String location, LocalDate targetDate, OrderType orderType) {
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isNullOrEmpty(location)) {
            builder.and(bookClubEntity.town.eq(location));
        }
        if (memberLimit != null) {
            builder.and(bookClubEntity.memberLimit.eq(memberLimit));
        }
        if (targetDate != null) {
            builder.and(bookClubEntity.targetDate.eq(targetDate));
        }
        //TODO: 정렬 기준 응답 오면 필요시 조건 추가하기

        return jpaQueryFactory.select(getBookClubDtoProjection())
                .from(bookClubEntity)
                .innerJoin(bookClubMemberEntity).on(bookClubEntity.id.eq(bookClubMemberEntity.bookClubId))
                .where(
                        filterEnum(bookClubType, bookClubEntity.bookClubType),
                        filterEnum(meetingType, bookClubEntity.meetingType),
                        builder)
                .groupBy(bookClubEntity.id)
                .orderBy(getOrderSpecifiers(orderType))
                .fetch();
    }

    private OrderSpecifier<?> getOrderSpecifiers(OrderType orderType) {
        if (orderType == OrderType.DESC) {
            return bookClubEntity.createdAt.desc();
        }
        if (orderType == OrderType.END) {
            return bookClubEntity.endDate.desc();
        }
        if (orderType == OrderType.SOON) {
            return bookClubEntity.targetDate.desc();
        }
        throw new IllegalArgumentException("지원하지 않는 정렬 타입입니다 : " + orderType);
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
                bookClubEntity.city,
                bookClubEntity.town,
                bookClubEntity.createdBy,
                bookClubEntity.createdAt,
                bookClubMemberEntity.count().intValue().as("memberCount"));
    }

    private <T extends Enum<T>> BooleanExpression filterEnum(T enumValue, EnumPath<T> enumPath) {
        if (enumValue.name().equals("ALL")) {
            return null;
        }
        return enumPath.eq(enumValue);
    }
}
