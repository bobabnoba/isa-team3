package com.ftn.fishingbooker.dto;

import lombok.Data;

@Data
public class UserRankDto {

    public Long id;

    public String name;

    public int minPoints;

    public double reservationPercentage;

    public double percentage;

}
