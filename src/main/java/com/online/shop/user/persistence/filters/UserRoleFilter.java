package com.online.shop.user.persistence.filters;

import com.online.shop.filters.InternalFieldFilter;
import com.online.shop.user.UserRole;
import com.online.shop.user.persistence.UserRepositoryImpl;

import java.util.Set;
import java.util.stream.Collectors;

import static com.online.shop.user.persistence.filters.UserFilterField.USER_ROLE;

public class UserRoleFilter extends InternalFieldFilter implements InternalUserFilter {

    public UserRoleFilter(Set<UserRole> roles) {
        super(roles.stream().map(UserRole::toString).collect(Collectors.toSet()),
                UserRepositoryImpl.TABLE,
                UserRepositoryImpl.ROLE,
                USER_ROLE.getName());
    }

    public UserRoleFilter(Set<UserRole> roles, String paramName) {
        super(roles.stream().map(UserRole::toString).collect(Collectors.toSet()),
                UserRepositoryImpl.TABLE,
                UserRepositoryImpl.ROLE,
                paramName);
    }
}
