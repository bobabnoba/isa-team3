package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.ClientReviewDto;
import com.ftn.fishingbooker.dto.ReviewDto;
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
        clientReview.setReservationType(clientReviewDto.getReservationType());
        return clientReview;
    }

    public static ReviewDto toDto(ClientReview clientReview) {

        ReviewDto dto = new ReviewDto();
        dto.setId(clientReview.getId());
        dto.setReview(clientReview.getReview());
        dto.setOwnerRating(clientReview.getOwnerRating());
        dto.setRentalRating(clientReview.getRentalRating());
        dto.setClientEmail(clientReview.getClientEmail());
        dto.setOwnerEmail(clientReview.getOwnerEmail());
        dto.setRentalName(clientReview.getRentalName());
        dto.setReservationType(clientReview.getReservationType());
        return dto;
    }
}
