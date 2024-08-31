package com.numan947.JobMicroService.job;

import com.numan947.JobMicroService.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    void createJob(JobModel job);
    JobModel findJobById(Long id);
    List<JobWithCompanyDTO> findAllJobs();

    void deleteJob(Long id);

    void updateJob(Long id, JobModel job);
}
