package com.numan947.CompanyMicroService.companies.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ReviewMicroService")
public interface ReviewClient {
    @GetMapping("/reviews/averageRating")
    public Double getAverageRating(@RequestParam Long companyId);
}
