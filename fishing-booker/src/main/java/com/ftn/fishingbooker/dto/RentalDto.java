package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.RentalType;
import com.ftn.fishingbooker.model.Address;
import lombok.Data;

@Data
public class RentalDto {

    private Long id;

    private String name;

    private Address address;

    private String description;

    private Double rating = 0.0;

    private RentalType rentalType;
}
