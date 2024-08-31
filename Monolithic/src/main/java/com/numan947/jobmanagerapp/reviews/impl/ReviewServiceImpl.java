package com.numan947.jobmanagerapp.reviews.impl;

import com.numan947.jobmanagerapp.companies.CompanyModel;
import com.numan947.jobmanagerapp.companies.CompanyRepository;
import com.numan947.jobmanagerapp.companies.CompanyService;
import com.numan947.jobmanagerapp.reviews.ReviewModel;
import com.numan947.jobmanagerapp.reviews.ReviewRepository;
import com.numan947.jobmanagerapp.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<ReviewModel> getAllReviews(Long companyId) {
        return reviewRepository.findAllByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, ReviewModel reviewModel) {
        CompanyModel company = companyService.getCompanyById(companyId).orElse(null);
        if (company == null) {
            return false;
        }else{
            reviewModel.setCompany(company);
            reviewRepository.save(reviewModel);
            return true;
        }
    }

    @Override
    public ReviewModel getReview(Long companyId, Long reviewId) {
        return reviewRepository.findAllByCompanyId(companyId)
                .stream()
                .filter(reviewModel -> reviewModel.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, ReviewModel reviewModel) {
        CompanyModel company = companyService.getCompanyById(companyId).orElse(null);
        if (company == null) {
            return false; // asserting that the company exists
        }else{
            //get the review by id
            ReviewModel review = reviewRepository.findAllByCompanyId(companyId)
                    .stream()
                    .filter(reviewModel1 -> reviewModel1.getId().equals(reviewId))
                    .findFirst()
                    .orElse(null);
            if(review == null){
                return false; // asserting that the review exists
            }else{
                reviewModel.setCompany(company);
                reviewModel.setId(reviewId);
                reviewRepository.save(reviewModel);
                return true;
            }
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        CompanyModel company = companyService.getCompanyById(companyId).orElse(null);
        if (company == null) {
            return false; // asserting that the company exists
        }else{
            //get the review by id
            ReviewModel review = reviewRepository.findAllByCompanyId(companyId)
                    .stream()
                    .filter(reviewModel1 -> reviewModel1.getId().equals(reviewId))
                    .findFirst()
                    .orElse(null);
            if(review == null){
                return false; // asserting that the review exists
            }else{
                reviewRepository.delete(review);
                return true;
            }
        }
    }

}
