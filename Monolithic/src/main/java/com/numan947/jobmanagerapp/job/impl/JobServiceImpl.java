package com.numan947.jobmanagerapp.job.impl;

import com.numan947.jobmanagerapp.job.JobModel;
import com.numan947.jobmanagerapp.job.JobRepository;
import com.numan947.jobmanagerapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository){
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
    public List<JobModel> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public void updateJob(Long id, JobModel job) {
        JobModel j = findJobById(id);
        if (j != null){
            j.setTitle(job.getTitle());
            j.setDescription(job.getDescription());
            j.setMinSalary(job.getMinSalary());
            j.setMaxSalary(job.getMaxSalary());
            j.setLocation(job.getLocation());
            jobRepository.save(j);
        }
    }
}
