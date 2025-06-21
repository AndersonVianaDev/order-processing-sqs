package com.anderson.order_worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.anderson.order_worker.infra.client")
public class OrderWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderWorkerApplication.class, args);
	}

}
