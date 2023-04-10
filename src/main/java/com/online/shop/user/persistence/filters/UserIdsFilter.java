package com.online.shop.user.persistence.filters;

import com.online.shop.filters.InternalIdsFilter;
import com.online.shop.user.persistence.UserRepositoryImpl;

import java.util.Set;
import java.util.UUID;

import static com.online.shop.user.persistence.filters.UserFilterField.USER_ID;

public class UserIdsFilter  extends InternalIdsFilter implements InternalUserFilter {

    public UserIdsFilter(Set<UUID> ids) {
        super(ids, UserRepositoryImpl.TABLE, UserRepositoryImpl.ID, USER_ID.getName());
    }
}
