package com.online.shop.user;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserService {
    List<User> getUsers(Set<UUID> userIds);

    User getUser(UUID id);

    void save(User user);
}
