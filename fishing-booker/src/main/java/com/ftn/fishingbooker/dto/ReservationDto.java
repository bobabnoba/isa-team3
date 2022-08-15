package com.ftn.fishingbooker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.fishingbooker.enumeration.ReservationType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class ReservationDto {
    private Long id;

    private double discount;

    private Boolean isCancelled;

    @Enumerated(EnumType.STRING)
    private ReservationType type;

    private int maxGuests;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    private float price;
}
