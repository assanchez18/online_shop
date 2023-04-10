package com.online.shop.containers;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import spock.lang.Specification;

@Testcontainers
public class ContainersConfig extends Specification {

    @Container
    public static PostgreSQLContainer userPostgreSQLContainer = UserPostgresTestContainer.getInstance();
}
