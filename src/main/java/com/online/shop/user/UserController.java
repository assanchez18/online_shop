package com.online.shop.user;


import com.online.shop.api.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserConverter userConverter;
    private final UserService userService;

    @GetMapping("/{userId}")
    public Set<UserDto> getUsers(@PathVariable UUID userId) {
        User a = userService.getUser(userId);
        return Set.of(new UserDto(UUID.randomUUID(), a.getName()));

    }

    @GetMapping("/hello")
    public String hello() {
        List<User> users = userService.getUsers(Set.of(UUID.randomUUID()));
        List<UserDto> user = users.stream()
                .map(userConverter::userToUserDto).toList();
        return "Hello " + user.get(0).getName();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody String name) {
        userService.save(new User(UUID.randomUUID(), name));
    }

}
