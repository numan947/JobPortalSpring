package com.numan947.jobmanagerapp.reviews;

import java.util.List;

public interface ReviewService {
    List<ReviewModel>getAllReviews(Long companyId);
    boolean addReview(Long companyId, ReviewModel reviewModel);

    ReviewModel getReview(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId, ReviewModel reviewModel);

    boolean deleteReview(Long companyId, Long reviewId);
}
