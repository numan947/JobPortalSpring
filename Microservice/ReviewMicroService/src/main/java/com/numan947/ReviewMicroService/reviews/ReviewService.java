package com.numan947.ReviewMicroService.reviews;

import java.util.List;

public interface ReviewService {
    List<ReviewModel>getAllReviews(Long companyId);
    boolean addReview(Long companyId, ReviewModel reviewModel);

    ReviewModel getReview(Long reviewId);

    boolean updateReview(Long reviewId, ReviewModel reviewModel);

    boolean deleteReview(Long reviewId);

    Double getAverageRating(Long companyId);
}
