package com.numan947.JobMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JobMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobMicroServiceApplication.class, args);
	}

}
