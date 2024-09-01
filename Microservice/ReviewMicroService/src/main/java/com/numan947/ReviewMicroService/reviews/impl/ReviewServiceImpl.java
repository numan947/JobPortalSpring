package com.numan947.ReviewMicroService.reviews.impl;


import com.numan947.ReviewMicroService.reviews.ReviewModel;
import com.numan947.ReviewMicroService.reviews.ReviewRepository;
import com.numan947.ReviewMicroService.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewModel> getAllReviews(Long companyId) {
        return reviewRepository.findAllByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, ReviewModel reviewModel) {
        if (reviewModel == null || companyId == null) {
            return false;
        }
        reviewModel.setCompanyId(companyId);
        reviewRepository.save(reviewModel);
        return true;
    }

    @Override
    public ReviewModel getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, ReviewModel updatedReview) {
        ReviewModel review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return false; // asserting that the review exists
        }else{
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            reviewRepository.save(review);
            return true;
        }
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        ReviewModel review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return false; // asserting that the review exists
        }else{
            reviewRepository.delete(review);
            return true;
        }
    }

    @Override
    public Double getAverageRating(Long companyId) {
        List<ReviewModel> reviews = reviewRepository.findAllByCompanyId(companyId);
        return reviews.stream().mapToDouble(ReviewModel::getRating).average().orElse(0.0);
    }

}
