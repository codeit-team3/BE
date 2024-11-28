package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubEntity;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubEntityRepository;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.CommandBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.QueryBookClubPort;
import com.codeit.sprint.team3.backend.bookclub.domain.model.BookClub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaBookClubAdapter implements CommandBookClubPort, QueryBookClubPort {
    private final BookClubEntityRepository bookClubEntityRepository;

    @Override
    public BookClub saveBookClub(BookClub bookClub) {
        return bookClubEntityRepository.save(BookClubEntity.from(bookClub))
                .toModel();
    }
}
