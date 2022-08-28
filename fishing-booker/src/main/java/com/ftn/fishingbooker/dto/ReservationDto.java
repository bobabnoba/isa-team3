package com.ftn.fishingbooker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.fishingbooker.enumeration.ReservationType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.Set;

@Data
public class ReservationDto {

    private Long id;

    private Boolean isCancelled;

    private ReservationType type;

    private int guests;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Belgrade")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Belgrade")
    private Date endDate;

    private double price;

    private Set<UtilityDto> utilities;

    private ReportDto report;

    private ClientReviewDto clientReview;


    private double cancelingPercentage;
}
