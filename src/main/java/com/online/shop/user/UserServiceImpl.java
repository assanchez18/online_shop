package com.online.shop.user;

import com.online.shop.user.persistence.UserEntity;
import com.online.shop.user.persistence.UserRepositoryImpl;
import com.online.shop.user.persistence.filters.InternalUserFilter;
import com.online.shop.user.persistence.filters.UserFilters;
import com.online.shop.user.persistence.filters.UserIdsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;
    @Override
    public List<User> getUsers(Set<UUID> userIds) {
        return convertUserEntityToUser(userRepository.getUsers(buildUserFilters(userIds)));
    }

    @Override
    public User getUser(UUID id) {
        List<User> users = getUsers(Set.of(id));
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    private List<User> convertUserEntityToUser(List<UserEntity> entities) {
        return entities.stream().map(User::new).toList();
    }

    private UserFilters buildUserFilters(Set<UUID> ids) {
        List<InternalUserFilter> filters = new ArrayList<>();
        filters.add(new UserIdsFilter(ids));
        return UserFilters.builder()
                .filters(filters)
                .build();
    }
}
