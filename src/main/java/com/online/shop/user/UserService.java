package com.online.shop.user;

import java.util.Set;
import java.util.UUID;

public interface UserService {
    Set<User> getUsers(Set<UUID> userIds);
}
