package com.codeit.sprint.team3.backend.auth.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

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
    private String nickName;

    @Column(nullable = false)
    private String description;

    public UserEntity(String name, String email, String password, String nickName, String description) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.description = description;
    }





}
