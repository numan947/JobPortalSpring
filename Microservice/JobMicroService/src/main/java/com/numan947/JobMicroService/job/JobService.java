package com.numan947.JobMicroService.job;

import java.util.List;

public interface JobService {
    void createJob(JobModel job);
    JobModel findJobById(Long id);
    List<JobModel> findAllJobs();

    void deleteJob(Long id);

    void updateJob(Long id, JobModel job);
}
