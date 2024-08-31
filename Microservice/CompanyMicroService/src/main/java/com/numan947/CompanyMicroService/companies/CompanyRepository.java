package com.numan947.CompanyMicroService.companies;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {
}
