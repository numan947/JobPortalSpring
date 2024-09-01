package com.numan947.JobMicroService.job;

import com.numan947.JobMicroService.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    void createJob(JobModel job);
    JobDTO findJobById(Long id);
    List<JobDTO> findAllJobs();
    boolean deleteJob(Long id);
    boolean updateJob(Long id, JobModel job);
}
