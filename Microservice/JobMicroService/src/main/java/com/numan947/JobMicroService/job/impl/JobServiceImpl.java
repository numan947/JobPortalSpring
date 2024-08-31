package com.numan947.JobMicroService.job.impl;

import com.numan947.JobMicroService.external.Company;
import com.numan947.JobMicroService.job.JobModel;
import com.numan947.JobMicroService.job.JobRepository;
import com.numan947.JobMicroService.job.JobService;
import com.numan947.JobMicroService.job.dto.JobWithCompanyDTO;
import com.numan947.JobMicroService.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    final RestTemplate loadBalancedRestTemplate;

    public JobServiceImpl(JobRepository jobRepository, RestTemplate loadBalancedRestTemplate) {
        this.jobRepository = jobRepository;
        this.loadBalancedRestTemplate = loadBalancedRestTemplate;
    }

    @Override
    public void createJob(JobModel job) {
        jobRepository.save(job);
    }

    @Override
    public JobWithCompanyDTO findJobById(Long id) {
        return getJobWithCompanyDTO(jobRepository.findById(id).orElse(null));
    }

    @Override
    public List<JobWithCompanyDTO> findAllJobs() {
        List<JobModel> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(this::getJobWithCompanyDTO)
                .collect(Collectors.toList());
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

    private JobWithCompanyDTO getJobWithCompanyDTO(JobModel j){
        if (j == null){
            return null;
        }
        Company c = loadBalancedRestTemplate.getForObject("http://CompanyMicroService:8081/companies/" + j.getCompanyId(), Company.class);
        return JobMapper.toJobWithCompanyDTO(j, c);
    }
}
