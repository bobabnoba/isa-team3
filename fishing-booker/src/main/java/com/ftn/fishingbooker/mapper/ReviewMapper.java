package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.ClientReviewDto;
import com.ftn.fishingbooker.model.ClientReview;

public class ReviewMapper {
    public static ClientReviewDto map(ClientReview clientReview) {

        ClientReviewDto clientReviewDto = new ClientReviewDto();
        clientReviewDto.setReview(clientReview.getReview());
        clientReviewDto.setOwnerRating(clientReview.getOwnerRating());
        clientReviewDto.setRentalRating(clientReview.getRentalRating());

        return clientReviewDto;
    }

    public static ClientReview map(ClientReviewDto clientReviewDto) {

        ClientReview clientReview = new ClientReview();
        clientReview.setReview(clientReviewDto.getReview());
        clientReview.setOwnerRating(clientReviewDto.getOwnerRating());
        clientReview.setRentalRating(clientReviewDto.getRentalRating());
        clientReview.setReservationId(clientReviewDto.getReservationId());
        return clientReview;
    }
}
