package com.codeit.sprint.team3.backend.auth.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Getter
public class User {
    private final Long id;
    private final String name;
    private final String nickname;
    private final String email;
    private final String description;
    private final String image;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;
}

