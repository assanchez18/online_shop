package com.online.shop.user.persistence.filters;

import com.online.shop.filters.FilterField;
import lombok.Getter;

@Getter
public enum UserFilterField implements FilterField {
    USER_ID("userIds");

    private final String name;
    UserFilterField(String name) {
        this.name = name;
    }
}
