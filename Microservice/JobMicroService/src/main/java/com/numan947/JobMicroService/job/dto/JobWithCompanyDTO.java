package com.numan947.JobMicroService.job.dto;

import com.numan947.JobMicroService.external.Company;
import com.numan947.JobMicroService.job.JobModel;

public class JobWithCompanyDTO {
    private JobModel job;
    private Company company;

    public JobWithCompanyDTO() {
    }

    public JobWithCompanyDTO(JobModel job, Company company) {
        this.job = job;
        this.company = company;
    }

    public JobModel getJob() {
        return job;
    }

    public void setJob(JobModel job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
