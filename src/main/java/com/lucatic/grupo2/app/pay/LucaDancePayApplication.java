package com.lucatic.grupo2.app.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LucaDancePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LucaDancePayApplication.class, args);
	}
}
