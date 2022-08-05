package com.ftn.fishingbooker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {
    private Date startDate;

    private Date endDate;

    private int maxGuests;

    private float price;
}
