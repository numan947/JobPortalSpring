package com.numan947.jobmanagerapp.companies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.numan947.jobmanagerapp.job.JobModel;
import com.numan947.jobmanagerapp.reviews.ReviewModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "company_table")
public class CompanyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<JobModel> jobs;

    //TODO: Add relationship with Reviews
    @OneToMany(mappedBy = "company")
    private List<ReviewModel> reviews;


    public CompanyModel() {
    }

    public CompanyModel(Long id, String name, String description, List<JobModel> jobs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobs = jobs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<JobModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobModel> jobs) {
        this.jobs = jobs;
    }
}
