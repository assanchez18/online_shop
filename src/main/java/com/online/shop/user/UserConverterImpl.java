package com.online.shop.user;

import com.online.shop.api.UserDto;
import com.online.shop.user.persistence.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverterImpl implements UserConverter{
    private final ModelMapper modelMapper;

    @Override
    public UserDto userToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User userEntityToUser(UserEntity user) {
        return modelMapper.map(user,User.class);
    }
}

