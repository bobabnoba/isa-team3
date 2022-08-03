package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.RentalType;
import lombok.Data;

@Data
public class RentalDto {

    private String name;

    private String address;

    private String description;

    private Double rating = 0.0;

    private RentalType rentalType;

    private String link;

}
