package com.online.shop.api;

import com.online.shop.user.User;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDto {

    private final UUID id;
    private final String name;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }
}
