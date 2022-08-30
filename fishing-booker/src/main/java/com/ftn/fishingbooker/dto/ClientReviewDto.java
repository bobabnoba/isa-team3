package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.ReservationType;
import lombok.Data;

@Data
public class ClientReviewDto {

    private Long reservationId;

    private String review;

    private Double ownerRating;

    private Double rentalRating;

    private ReservationType reservationType;

}
