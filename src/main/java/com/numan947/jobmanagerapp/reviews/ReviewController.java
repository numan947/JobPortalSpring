package com.numan947.jobmanagerapp.reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(path = {"/reviews", "/reviews/"})
    public ResponseEntity<List<ReviewModel>> getAllReviews(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @PostMapping(path = {"/reviews", "/reviews/"})
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody ReviewModel reviewModel) {
        if(reviewService.addReview(companyId, reviewModel))
            return new ResponseEntity<>("Review created", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = {"/reviews/{reviewId}", "/reviews/{reviewId}/"})
    public ResponseEntity<ReviewModel> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        ReviewModel reviewModel = reviewService.getReview(companyId, reviewId);
        if(reviewModel != null)
            return ResponseEntity.ok(reviewModel);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = {"/reviews/{reviewId}", "/reviews/{reviewId}/"})
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody ReviewModel reviewModel) {
        if(reviewService.updateReview(companyId, reviewId, reviewModel))
            return new ResponseEntity<>("Review updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review or company not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = {"/reviews/{reviewId}", "/reviews/{reviewId}/"})
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        if(reviewService.deleteReview(companyId, reviewId))
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review or company not found", HttpStatus.NOT_FOUND);
    }


}
