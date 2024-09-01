package com.numan947.CompanyMicroService.companies.impl;

import com.numan947.CompanyMicroService.companies.CompanyModel;
import com.numan947.CompanyMicroService.companies.CompanyRepository;
import com.numan947.CompanyMicroService.companies.CompanyService;
import com.numan947.CompanyMicroService.companies.clients.ReviewClient;
import com.numan947.CompanyMicroService.companies.dto.ReviewMessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
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

    @Override
    public void updateCompanyRating(ReviewMessageDTO reviewMessageDTO) {
        Optional<CompanyModel> op = companyRepository.findById(reviewMessageDTO.getCompanyId());
        if (op.isEmpty()) {
            return;
        }
        CompanyModel companyModel = op.get();
        Double avgRating = reviewClient.getAverageRating(companyModel.getId());
        companyModel.setAverageRating(avgRating);
        companyRepository.save(companyModel);
    }
}
