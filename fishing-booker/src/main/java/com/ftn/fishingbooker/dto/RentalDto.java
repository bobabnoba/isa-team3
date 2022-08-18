package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.RentalType;
import com.ftn.fishingbooker.model.Address;
import lombok.Data;

import java.util.Collection;

@Data
public class RentalDto {

    private Long id;

    private String name;

    private Address address;

    private Collection<UtilityDto> utilities;

    private double pricePerDay;

    private String description;

    private Double rating = 0.0;

    private Double duration;

    private RentalType rentalType;

    private OwnerDto owner;
}
