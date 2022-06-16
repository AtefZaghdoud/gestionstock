package com.Atef.gestionstock;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestionDeStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeStockApplication.class);
	}

}
