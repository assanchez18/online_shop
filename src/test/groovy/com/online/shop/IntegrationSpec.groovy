package com.online.shop

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.sql.DataSource

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [TiendaOnlineApplication.class])
@ActiveProfiles("test")
class IntegrationSpec extends Specification {
    @Autowired
    DataSource dataSource

    ArrayList<String> tables = [
            'users'
    ]

    def setup() {
        truncate(tables)
    }

    def cleanup() {
        truncate(tables)
    }

    def truncate(tables) {
        //def jdbcTemplate = new JdbcTemplate(dataSource: dataSource)
        //tables.forEach { table -> jdbcTemplate.execute("TRUNCATE TABLE ${table}") }
    }
}
