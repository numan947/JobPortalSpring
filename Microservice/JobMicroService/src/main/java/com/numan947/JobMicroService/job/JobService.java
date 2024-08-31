package com.numan947.JobMicroService.job;

import com.numan947.JobMicroService.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    void createJob(JobModel job);
    JobWithCompanyDTO findJobById(Long id);
    List<JobWithCompanyDTO> findAllJobs();
    boolean deleteJob(Long id);
    boolean updateJob(Long id, JobModel job);
}
