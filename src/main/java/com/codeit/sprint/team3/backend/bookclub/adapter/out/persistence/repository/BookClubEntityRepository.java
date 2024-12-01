package com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.repository;

import com.codeit.sprint.team3.backend.bookclub.adapter.out.persistence.entity.BookClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookClubEntityRepository extends JpaRepository<BookClubEntity, Long> {
}
