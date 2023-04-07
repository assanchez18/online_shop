package com.online.shop.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class User {

    private UUID id;
    private String name;
    private UserRole role;

    public User(String name) {
        this.id = null;
        this.name = name;
        this.role = UserRole.EMPLOYEE;
    }
}
