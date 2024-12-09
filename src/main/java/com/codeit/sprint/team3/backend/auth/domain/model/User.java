package com.codeit.sprint.team3.backend.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class User {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String description;
    private String image;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

