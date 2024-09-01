package com.numan947.ReviewMicroService.reviews.messaging;

import com.netflix.discovery.converters.Auto;
import com.numan947.ReviewMicroService.reviews.ReviewModel;
import com.numan947.ReviewMicroService.reviews.dto.ReviewMessageDTO;
import jakarta.ws.rs.Produces;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ReviewModel review) {
        if (review == null) {
            return;
        }
        ReviewMessageDTO reviewMessageDTO = new ReviewMessageDTO();
        reviewMessageDTO.setId(review.getId());
        reviewMessageDTO.setTitle(review.getTitle());
        reviewMessageDTO.setDescription(review.getDescription());
        reviewMessageDTO.setRating(review.getRating());
        reviewMessageDTO.setCompanyId(review.getCompanyId());
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessageDTO);
    }
}
