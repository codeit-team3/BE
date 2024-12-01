package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubEntity;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubEntityRepository;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubQueryRepository;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.CommandBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.QueryBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubType;
import com.codeit.sprint.team3.backend.bookclub.domain.MeetingType;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaBookClubAdapter implements CommandBookClubPort, QueryBookClubPort {
    private final BookClubEntityRepository bookClubEntityRepository;
    private final BookClubQueryRepository bookClubQueryRepository;

    @Override
    public BookClub saveBookClub(BookClub bookClub, Long userId) {
        return bookClubEntityRepository.save(BookClubEntity.of(bookClub, userId))
                .toModel();
    }

    @Override
    public List<BookClub> findBookClubsBy(BookClubType bookClubType, MeetingType meetingType, Integer memberLimit, String location, LocalDate targetDate) {
        //TODO: 찜 구현 후 찜 추가하기
        return bookClubQueryRepository.findBookClubsBy(bookClubType, meetingType, memberLimit, location, targetDate)
                .stream()
                .map(BookClubEntity::toModel)
                .toList();
    }

    @Override
    public Optional<BookClub> getById(Long bookClubId) {
        return bookClubEntityRepository.findById(bookClubId)
                .map(BookClubEntity::toModel);
    }
}
