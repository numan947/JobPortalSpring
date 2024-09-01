package com.numan947.JobMicroService.job.mapper;

import com.numan947.JobMicroService.job.external.Company;
import com.numan947.JobMicroService.job.external.Review;
import com.numan947.JobMicroService.job.JobModel;
import com.numan947.JobMicroService.job.dto.JobDTO;

import java.util.List;

public class JobMapper {
    public static JobDTO toJobDTO(JobModel jobModel, Company company, List<Review> reviews) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(jobModel.getId());
        jobDTO.setTitle(jobModel.getTitle());
        jobDTO.setDescription(jobModel.getDescription());
        jobDTO.setMinSalary(jobModel.getMinSalary());
        jobDTO.setMaxSalary(jobModel.getMaxSalary());
        jobDTO.setLocation(jobModel.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
