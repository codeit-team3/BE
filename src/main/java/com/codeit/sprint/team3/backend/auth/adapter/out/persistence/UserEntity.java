package com.codeit.sprint.team3.backend.auth.adapter.out.persistence;

import com.codeit.sprint.team3.backend.auth.domain.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String description;

    public UserEntity(String name, String email, String password, String nickname, String description) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.description = description;
    }

    public User toUser() {
        return new User(id, name, nickname, email, description, "image not yet implemented", LocalDateTime.now(), LocalDateTime.now());
    }



}
