package com.numan947.JobMicroService.job.mapper;

import com.numan947.JobMicroService.external.Company;
import com.numan947.JobMicroService.job.JobModel;
import com.numan947.JobMicroService.job.dto.JobWithCompanyDTO;

public class JobMapper {
    public static JobWithCompanyDTO toJobWithCompanyDTO(JobModel jobModel, Company company) {
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(jobModel.getId());
        jobWithCompanyDTO.setTitle(jobModel.getTitle());
        jobWithCompanyDTO.setDescription(jobModel.getDescription());
        jobWithCompanyDTO.setMinSalary(jobModel.getMinSalary());
        jobWithCompanyDTO.setMaxSalary(jobModel.getMaxSalary());
        jobWithCompanyDTO.setLocation(jobModel.getLocation());
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;
    }
}
