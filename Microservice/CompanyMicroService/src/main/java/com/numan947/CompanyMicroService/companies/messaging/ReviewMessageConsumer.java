package com.numan947.CompanyMicroService.companies.messaging;

import com.numan947.CompanyMicroService.companies.CompanyService;
import com.numan947.CompanyMicroService.companies.dto.ReviewMessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {
    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessageDTO reviewMessageDTO) {
        if (reviewMessageDTO == null) {
            return;
        }
        companyService.updateCompanyRating(reviewMessageDTO);
    }
}
