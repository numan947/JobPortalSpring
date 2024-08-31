package com.numan947.JobMicroService.job.impl;

import com.numan947.JobMicroService.external.Company;
import com.numan947.JobMicroService.job.JobModel;
import com.numan947.JobMicroService.job.JobRepository;
import com.numan947.JobMicroService.job.JobService;
import com.numan947.JobMicroService.job.dto.JobWithCompanyDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void createJob(JobModel job) {
        jobRepository.save(job);
    }

    @Override
    public JobModel findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public List<JobWithCompanyDTO> findAllJobs() {
        List<JobModel> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(this::getJobWithCompanyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public void updateJob(Long id, JobModel job) {
        JobModel j = findJobById(id);
        if (j != null) {
            j.setTitle(job.getTitle());
            j.setDescription(job.getDescription());
            j.setMinSalary(job.getMinSalary());
            j.setMaxSalary(job.getMaxSalary());
            j.setLocation(job.getLocation());
            jobRepository.save(j);
        }
    }

    private JobWithCompanyDTO getJobWithCompanyDTO(JobModel j){
        if (j == null){
            return null;
        }
        RestTemplate rt = new RestTemplate();
        Company c = rt.getForObject("http://localhost:8081/companies/" + j.getCompanyId(), Company.class);
        return new JobWithCompanyDTO(j, c);
    }
}
