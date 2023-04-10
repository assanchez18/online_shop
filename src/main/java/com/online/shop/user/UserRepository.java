package com.online.shop.user;

import com.online.shop.user.persistence.UserEntity;
import com.online.shop.user.persistence.filters.UserFilters;

import java.util.List;

public interface UserRepository {

    List<UserEntity> getUsers(UserFilters userIds);
    void save(User user);

}
