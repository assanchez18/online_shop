package com.online.shop.user;

import com.online.shop.user.persistence.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;
    @Override
    public Set<User> getUsers(Set<UUID> userIds) {
        Set<User> users = userRepository.getUsers(userIds);
        return users;
    }
}
