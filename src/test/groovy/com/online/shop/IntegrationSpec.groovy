package com.online.shop

import com.online.shop.user.persistence.UserRepositoryImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.sql.DataSource

@SpringBootTest
@ContextConfiguration(classes = [TiendaOnlineApplication.class])
@ActiveProfiles("test")
class IntegrationSpec extends Specification {
    @Autowired
    DataSource dataSource

    ArrayList<String> tables = [
            UserRepositoryImpl.TABLE
    ]

    def setup() {
        truncate(tables)
    }

    def cleanup() {
        truncate(tables)
    }

    def truncate(tables) {
        def jdbcTemplate = new JdbcTemplate(dataSource: dataSource)
        tables.forEach { table -> jdbcTemplate.execute("TRUNCATE TABLE ${table}") }
    }
}
