package com.online.shop.user

import com.online.shop.IntegrationSpec
import com.online.shop.user.persistence.UserEntity
import com.online.shop.user.persistence.UserRepositoryImpl
import com.online.shop.user.persistence.filters.UserFilters
import com.online.shop.user.persistence.filters.UserIdsFilter
import org.springframework.beans.factory.annotation.Autowired

class UserRepositoryIntSpec extends IntegrationSpec {

    @Autowired
    UserRepositoryImpl repository

    def "Get Users works"() {
        given:
            UUID userId = UUID.randomUUID()
            User user = new User(userId, "TestName", UserRole.USER)
            User notFoundUser = new User(UUID.randomUUID(), "notFound", UserRole.USER)
            repository.save(user)
            repository.save(notFoundUser)
            UserFilters filters = UserFilters.builder().filters(List.of(new UserIdsFilter(Set.of(userId)))).build()
        when:
            List<UserEntity> result = repository.getUsers(filters)
        then:
            result.size() == 1
            result.get(0).id == userId
    }
}
