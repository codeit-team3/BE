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
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String description;
    private String image;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public void update(UpdateUserProfileCommand command) {
        nickname = command.getNickname()==null? nickname : command.getNickname();
        image = command.getImage()==null? image : command.getImage();
        description = command.getDescription()==null? description : command.getDescription();
        updatedAt = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    }

}

