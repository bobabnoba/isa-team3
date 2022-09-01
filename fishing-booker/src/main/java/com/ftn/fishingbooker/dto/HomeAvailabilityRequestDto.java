package com.ftn.fishingbooker.dto;

import lombok.*;

import java.util.*;

@Data
public class HomeAvailabilityRequestDto {
    public Date startDate;

    public Date endDate;

    public Long homeId;
}
