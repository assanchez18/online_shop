package com.online.shop.user

import com.online.shop.IntegrationSpec
import com.online.shop.api.UserDto
import org.springframework.beans.factory.annotation.Autowired

class UserControllerIntSpec extends IntegrationSpec {

    @Autowired
    UserController userController

    def "Sample test one"() {
        given: "ID"
            UUID id = UUID.randomUUID()
        when: "get all users by Id"
            Set<UserDto> users = userController.getUsers(Set.of(id))
        then: "UserDto is expected"
            users.size() == 1
            users[0].getId() == id
            //users[0].getName() == id.toString()
    }
}
