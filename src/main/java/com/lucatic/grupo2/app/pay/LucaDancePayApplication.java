package com.lucatic.grupo2.app.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal
 * 
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 19-03-2024
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LucaDancePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LucaDancePayApplication.class, args);
	}
}
