package com.codeit.sprint.team3.backend.auth.adapter.out.persistence;

import com.codeit.sprint.team3.backend.auth.domain.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity implements UserDetails {
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
    private String image;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Column(nullable = false)
    private ZonedDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity(String name, String email, String password, String image, String nickname, String description, ZonedDateTime createdAt, ZonedDateTime updatedAt, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
        this.nickname = nickname;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
    }

    public User toDomain() {
        return new User(id, name, nickname, email, description, image, createdAt, updatedAt);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

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
