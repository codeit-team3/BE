package com.codeit.sprint.team3.backend.auth.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.domain.model.User;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UserProfileDto {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String description;
    private String image;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public static UserProfileDto from(User user) {
        return UserProfileDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .description(user.getDescription())
                .image(user.getImage())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
