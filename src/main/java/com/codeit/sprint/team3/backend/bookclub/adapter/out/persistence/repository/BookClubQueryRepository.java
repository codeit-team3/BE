package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubEntity;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.QBookClubEntity.bookClubEntity;

@RequiredArgsConstructor
@Repository
public class BookClubQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<BookClubEntity> findBookClubsBy(BookClubType bookClubType, MeetingType meetingType, Integer memberLimit, String location, LocalDate targetDate) {
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isNullOrEmpty(location)) {
            builder.and(bookClubEntity.city.eq(location));
        }
        if (memberLimit != null) {
            builder.and(bookClubEntity.memberLimit.eq(memberLimit));
        }
        if (targetDate != null) {
            builder.and(bookClubEntity.targetDate.eq(targetDate));
        }
        return jpaQueryFactory.selectFrom(bookClubEntity)
                .where(
                        filterEnum(bookClubType, bookClubEntity.bookClubType),
                        filterEnum(meetingType, bookClubEntity.meetingType),
                        builder)
                .fetch();
    }

    private <T extends Enum<T>> BooleanExpression filterEnum(T enumValue, EnumPath<T> enumPath) {
        if (enumValue.name().equals("ALL")) {
            return null;
        }
        return enumPath.eq(enumValue);
    }
}
