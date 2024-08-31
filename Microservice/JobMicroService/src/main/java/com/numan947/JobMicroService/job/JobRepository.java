package com.numan947.JobMicroService.job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobModel, Long> {

}
