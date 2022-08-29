package com.ftn.fishingbooker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.fishingbooker.enumeration.ReservationType;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
public class ReservationWithClientDto {
    private Long id;

    private Boolean isCancelled;

    private ReservationType type;

    private int guests;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Belgrade")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Belgrade")
    private Date endDate;

    private double price;

    private Collection<UtilityDto> utilities;

    private ReportDto report;

    private UserDto client;

    private double cancelingPercentage;
}
