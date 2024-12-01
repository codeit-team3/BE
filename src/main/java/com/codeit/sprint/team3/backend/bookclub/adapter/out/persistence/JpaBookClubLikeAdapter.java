package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubLikeEntity;
import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository.BookClubLikeEntityRepository;
import com.codeit.sprint.team3.backend.bookclub.application.port.out.BookClubLikePort;
import com.codeit.sprint.team3.backend.bookclub.domain.BookClubLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaBookClubLikeAdapter implements BookClubLikePort {
    private final BookClubLikeEntityRepository bookClubLikeEntityRepository;

    @Override
    public void saveBookClubLike(BookClubLike bookClubLike) {
        Optional<BookClubLikeEntity> bookClubLikeEntity = bookClubLikeEntityRepository.findByBookClubIdAndUserId(bookClubLike.getBookClubId(), bookClubLike.getUserId());
        if (bookClubLikeEntity.isPresent()) {
            return;
        }
        bookClubLikeEntityRepository.save(BookClubLikeEntity.from(bookClubLike));
    }

    @Override
    public void deleteBookClubLike(BookClubLike bookClubLike) {
        bookClubLikeEntityRepository.findByBookClubIdAndUserId(bookClubLike.getBookClubId(), bookClubLike.getUserId())
                .ifPresent(bookClubLikeEntityRepository::delete);
    }
}
