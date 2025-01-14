package com.codeit.sprint.team3.backend.auth.domain.model;

import com.codeit.sprint.team3.backend.auth.application.port.in.UpdateUserProfileCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class User {
    private final static User EMPTY = new User(0L, null, null, null, null, null, null, null);

    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String description;
    private String image;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public static User getEmpty() {
        return EMPTY;
    }

    public Boolean isEmpty() {
        return this == EMPTY;
    }

    public void update(UpdateUserProfileCommand command) {
        nickname = command.getNickname() == null ? nickname : command.getNickname();
        description = command.getDescription() == null ? description : command.getDescription();
        updatedAt = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public void setImage(String imageURI) {
        this.image = imageURI;
    }

}

