package com.numan947.jobmanagerapp.companies.impl;

import com.numan947.jobmanagerapp.companies.CompanyModel;
import com.numan947.jobmanagerapp.companies.CompanyRepository;
import com.numan947.jobmanagerapp.companies.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyModel> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<CompanyModel> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public boolean createCompany(CompanyModel company) {
        companyRepository.save(company);
        return true;
    }

    @Override
    public boolean updateCompany(Long id, CompanyModel company) {
        Optional<CompanyModel> op = companyRepository.findById(id);
        if (op.isEmpty()) {
            return false;
        }
        CompanyModel companyModel = op.get();
        companyModel.setName(company.getName());
        companyModel.setDescription(company.getDescription());
        companyModel.setJobs(company.getJobs());
        companyRepository.save(companyModel);
        return true;
    }

    @Override
    public boolean deleteCompany(Long id) {
        Optional<CompanyModel> op = companyRepository.findById(id);
        if (op.isEmpty()) {
            return false;
        }
        CompanyModel companyModel = op.get();
        companyRepository.delete(companyModel);
        return true;
    }
}
