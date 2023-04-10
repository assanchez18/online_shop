package com.online.shop.containers

import org.testcontainers.containers.PostgreSQLContainer

class UserPostgresTestContainer extends PostgreSQLContainer<UserPostgresTestContainer> {
    public static final String IMAGE_VERSION = "postgres:42.5";
    public static final String DATABASE_NAME = "usersTest";
    public static PostgreSQLContainer container;

    UserPostgresTestContainer() {
        super(IMAGE_VERSION);
    }

    static PostgreSQLContainer getInstance() {
        if (container == null) {
            container = new UserPostgresTestContainer().withDatabaseName(DATABASE_NAME);
        }
        return container;
    }

    @Override
    void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    void stop() {
    }
}
