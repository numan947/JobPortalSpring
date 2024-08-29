package com.numan947.jobmanagerapp.job.impl;

import com.numan947.jobmanagerapp.job.JobModel;
import com.numan947.jobmanagerapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<JobModel> jobs =  new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public void createJob(JobModel job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public JobModel findJobById(Long id) {
        return jobs.stream().filter(job -> job.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<JobModel> findAllJobs() {
        return jobs;
    }

    @Override
    public void deleteJob(Long id) {
        jobs.removeIf(job -> job.getId().equals(id));
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
        }
    }
}
