package com.lucatic.grupo2.app.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LucaDancePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LucaDancePayApplication.class, args);
	}

}
