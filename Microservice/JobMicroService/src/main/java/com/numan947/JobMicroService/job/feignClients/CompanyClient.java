package com.numan947.JobMicroService.job.feignClients;

import com.numan947.JobMicroService.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CompanyMicroService")
public interface CompanyClient {
    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable Long id);
}
