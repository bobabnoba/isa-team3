package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.ReviewStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class ClientReviewDto {

    private Long id;

    private String review;

    private Double rating;

    private Date datePosted;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    private Long reservationId;

}
