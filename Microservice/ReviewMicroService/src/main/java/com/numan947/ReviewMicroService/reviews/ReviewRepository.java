package com.numan947.ReviewMicroService.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewModel, Long> {
    List<ReviewModel> findAllByCompanyId(Long companyId);
}
