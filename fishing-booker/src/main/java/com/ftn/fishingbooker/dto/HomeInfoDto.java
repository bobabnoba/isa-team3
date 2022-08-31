package com.ftn.fishingbooker.dto;

import lombok.*;

@Data
public class HomeInfoDto {
    public String name;
    public double pricePerDay;
    public String description;
    public String information;
    public int guestLimit;
    public double cancelingPercentage;
}
