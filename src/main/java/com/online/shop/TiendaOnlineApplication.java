package com.online.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.online.shop.user.persistence")
public class TiendaOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaOnlineApplication.class, args);
	}

}
