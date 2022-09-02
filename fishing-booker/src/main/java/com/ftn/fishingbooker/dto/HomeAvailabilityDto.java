package com.ftn.fishingbooker.dto;

import lombok.*;

import java.util.*;

@Data
public class HomeAvailabilityDto {
    public Long id;
    public Date startDate;
    public Date endDate;
}
