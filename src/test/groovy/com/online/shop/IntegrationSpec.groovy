package com.online.shop


import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration(classes = [TiendaOnlineApplication.class])
class IntegrationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }
}
