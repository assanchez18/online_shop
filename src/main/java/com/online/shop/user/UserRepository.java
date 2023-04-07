package com.online.shop.user;

import com.online.shop.user.persistence.UserEntity;

import java.util.Set;
import java.util.UUID;

public interface UserRepository {//extends Repository<UserEntity, UUID> {

    Set<User> getUsers(Set<UUID> userIds);
    UserEntity getUser(UUID id);
    UserEntity save(User user);
}
