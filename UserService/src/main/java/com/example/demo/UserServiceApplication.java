package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	// @LoadBalanced Allows RestTemplate to call services by name from Eureka
	@LoadBalanced // RestTemplate, when annotated with @LoadBalanced, uses service names
					// registered in Eureka instead of hardcoded hostnames and ports.
	public RestTemplate restTemplate() {
		return new RestTemplate();

	}
}
