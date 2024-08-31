package com.numan947.JobMicroService.job;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @LoadBalanced
    @Bean
    public RestTemplate loadBalancedRestTemplate(){
        return new RestTemplate();
    }
}
