package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.ClientReviewDto;
import com.ftn.fishingbooker.model.ClientReview;

public class ReviewMapper {
    public static ClientReviewDto map(ClientReview clientReview) {

        ClientReviewDto clientReviewDto = new ClientReviewDto();
        clientReviewDto.setReview(clientReview.getReview());
        clientReviewDto.setId(clientReview.getId());
        clientReviewDto.setRating(clientReview.getRating());
        clientReviewDto.setDatePosted(clientReview.getDatePosted());
        clientReviewDto.setStatus(clientReview.getStatus());

        return clientReviewDto;
    }

    public static ClientReview map(ClientReviewDto clientReviewDto) {

        ClientReview clientReview = new ClientReview();
        clientReview.setReview(clientReviewDto.getReview());
        clientReview.setId(clientReviewDto.getId());
        clientReview.setRating(clientReviewDto.getRating());
        clientReview.setDatePosted(clientReviewDto.getDatePosted());
        clientReview.setStatus(clientReviewDto.getStatus());
        return clientReview;
    }
}
