package com.numan947.JobMicroService.job.impl;

import com.numan947.JobMicroService.job.feignClients.CompanyClient;
import com.numan947.JobMicroService.job.feignClients.ReviewClient;
import com.numan947.JobMicroService.job.external.Company;
import com.numan947.JobMicroService.job.external.Review;
import com.numan947.JobMicroService.job.JobModel;
import com.numan947.JobMicroService.job.JobRepository;
import com.numan947.JobMicroService.job.JobService;
import com.numan947.JobMicroService.job.dto.JobDTO;
import com.numan947.JobMicroService.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    final CompanyClient companyClient;
    final ReviewClient reviewClient;
    final RestTemplate loadBalancedRestTemplate;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient, RestTemplate loadBalancedRestTemplate) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
        this.loadBalancedRestTemplate = loadBalancedRestTemplate;
    }

    @Override
    public void createJob(JobModel job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO findJobById(Long id) {
        return getJobDTO(jobRepository.findById(id).orElse(null));
    }

    @Override
//    @CircuitBreaker(name="companyBreaker", fallbackMethod = "findAllJobsFallback")
//    @Retry(name="companyBreaker", fallbackMethod = "findAllJobsFallback")
    @RateLimiter(name="companyBreaker", fallbackMethod = "findAllJobsFallback")
    public List<JobDTO> findAllJobs() {
        List<JobModel> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(this::getJobDTO)
                .collect(Collectors.toList());
    }
    // Fallback method for findAllJobs
    public List<String> findAllJobsFallback(Exception e){
        return List.of("Company service is down. Please try again later.");
    }

    @Override
    public boolean deleteJob(Long id) {
        JobModel j = jobRepository.findById(id).orElse(null);
        if (j == null){
            return false;
        }
        jobRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateJob(Long id, JobModel job) {
        JobModel j = jobRepository.findById(id).orElse(null);
        if (j != null) {
            j.setTitle(job.getTitle());
            j.setDescription(job.getDescription());
            j.setMinSalary(job.getMinSalary());
            j.setMaxSalary(job.getMaxSalary());
            j.setLocation(job.getLocation());
            jobRepository.save(j);
            return true;
        }
        return false;
    }

    private JobDTO getJobDTO(JobModel j){
        if (j == null){
            return null;
        }
        Company c = companyClient.getCompany(j.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(j.getId());
        return JobMapper.toJobDTO(j, c, reviews);
    }
}
