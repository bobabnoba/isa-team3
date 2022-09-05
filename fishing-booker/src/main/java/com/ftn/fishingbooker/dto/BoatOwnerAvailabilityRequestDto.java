package com.ftn.fishingbooker.dto;

import lombok.*;

import java.util.*;

@Data
public class BoatOwnerAvailabilityRequestDto {
    public Date startDate;

    public Date endDate;

    public String email;
}
