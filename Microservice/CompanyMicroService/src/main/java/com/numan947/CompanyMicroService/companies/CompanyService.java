package com.numan947.CompanyMicroService.companies;

import com.numan947.CompanyMicroService.companies.dto.ReviewMessageDTO;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<CompanyModel> getAllCompanies();
    Optional<CompanyModel> getCompanyById(Long id);
    boolean createCompany(CompanyModel company);
    boolean updateCompany(Long id, CompanyModel company);
    boolean deleteCompany(Long id);
    void updateCompanyRating(ReviewMessageDTO reviewMessageDTO);
}
