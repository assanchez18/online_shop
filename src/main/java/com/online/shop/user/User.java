package com.online.shop.user;

import com.online.shop.user.persistence.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class User {

    private UUID id;
    private String name;
    private UserRole role;

    public User(UUID userId, String name) {
        this.id = userId;
        this.name = name;
        this.role = UserRole.EMPLOYEE;
    }

    public User(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = UserRole.EMPLOYEE;
    }
}
