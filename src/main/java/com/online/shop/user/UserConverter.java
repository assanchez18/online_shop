package com.online.shop.user;

import com.online.shop.api.UserDto;
import com.online.shop.user.persistence.UserEntity;

public interface UserConverter {
    UserDto userToUserDto(User user);

    User userEntityToUser(UserEntity user);
}
