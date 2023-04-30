package com.online.shop.user;

import com.online.shop.user.persistence.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class User {

    private UUID id;
    private String name;
    private UserRole role;
    private Timestamp creationTime;

    public User(UUID userId, String name, UserRole role) {
        this.id = userId;
        this.name = name;
        this.role = role;
    }

    public User(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
        this.creationTime = user.getCreationTime();
    }
}
