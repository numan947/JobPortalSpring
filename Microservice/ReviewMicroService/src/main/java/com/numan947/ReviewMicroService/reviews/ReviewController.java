package com.numan947.ReviewMicroService.reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<ReviewModel>> getAllReviews(@RequestParam Long companyId) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody ReviewModel reviewModel) {
        if(reviewService.addReview(companyId, reviewModel))
            return new ResponseEntity<>("Review created", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = {"/{reviewId}", "/{reviewId}/"})
    public ResponseEntity<ReviewModel> getReview(@PathVariable Long reviewId) {
        ReviewModel reviewModel = reviewService.getReview(reviewId);
        if(reviewModel != null)
            return ResponseEntity.ok(reviewModel);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = {"/{reviewId}", "/{reviewId}/"})
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody ReviewModel reviewModel) {
        if(reviewService.updateReview(reviewId, reviewModel))
            return new ResponseEntity<>("Review updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review or company not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = {"/{reviewId}", "/{reviewId}/"})
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        if(reviewService.deleteReview(reviewId))
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review or company not found", HttpStatus.NOT_FOUND);
    }


}
