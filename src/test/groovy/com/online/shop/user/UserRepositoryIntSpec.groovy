package com.online.shop.user

import com.online.shop.IntegrationSpec
import com.online.shop.user.persistence.UserEntity
import com.online.shop.user.persistence.UserRepositoryImpl
import com.online.shop.user.persistence.filters.UserFilters
import org.springframework.beans.factory.annotation.Autowired

class UserRepositoryIntSpec extends IntegrationSpec {

    @Autowired
    UserRepositoryImpl repository

    def "Get Users filter by Id works"() {
        given:
            UUID userId = UUID.randomUUID()
            User user = new User(userId, "TestName", UserRole.EMPLOYEE)
            User notFoundUser = new User(UUID.randomUUID(), "notFound", UserRole.USER)
            repository.save(user)
            repository.save(notFoundUser)
            UserFilters filters = UserFilters.builder().withUserId(userId).build()
        when:
            List<UserEntity> result = repository.getUsers(filters)
        then:
            result.size() == 1
            result.get(0).id == userId
            result.get(0).name == "TestName"
            result.get(0).role == UserRole.EMPLOYEE
    }

    def "Get Users filter by role works"() {
        given:
            UUID userId = UUID.randomUUID()
            User user = new User(userId, "TestName", UserRole.EMPLOYEE)
            User notFoundUser = new User(UUID.randomUUID(), "notFound", UserRole.USER)
            repository.save(user)
            repository.save(notFoundUser)
            UserFilters filters = UserFilters.builder().withUserRole(UserRole.EMPLOYEE).build()
        when:
            List<UserEntity> result = repository.getUsers(filters)
        then:
            result.size() == 1
            result.get(0).id == userId
            result.get(0).name == "TestName"
            result.get(0).role == UserRole.EMPLOYEE
    }

    def "Get Users filter by Id or role works"() {
        given:
            UUID userId = UUID.randomUUID()
            UUID userId2 = UUID.randomUUID()
            Set<UUID> expectedIds = Set.of(userId, userId2)
            User user = new User(userId, "TestName", UserRole.EMPLOYEE)
            User user2 = new User(userId2, "TestName", UserRole.USER)
            User notFoundUser = new User(UUID.randomUUID(), "notFound", UserRole.EMPLOYEE)
            repository.save(user)
            repository.save(user2)
            repository.save(notFoundUser)
            UserFilters filters = UserFilters.builder().withTeamLead(Set.of(userId), UserRole.USER).build()
        when:
            List<UserEntity> result = repository.getUsers(filters)
        then:
            Set<UserEntity> entities = new HashSet<>(result)
            entities.size() == 2
            entities.stream().map(UserEntity::getId).each {
                assert expectedIds.contains(it)
            }
    }

}
