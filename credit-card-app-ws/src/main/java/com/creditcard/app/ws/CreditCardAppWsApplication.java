package com.creditcard.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.creditcard.app.ws.io.entity"})
public class CreditCardAppWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardAppWsApplication.class, args);
	}

}
