package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.Address;
import lombok.Data;

import java.util.Collection;

@Data
public class VacationHomeDto {
    private Long id;

    private String name;

    private Address address;

    private String description;

    private double rating;

    private double pricePerDay;

    private Integer guestLimit;

    private Collection<ImageDto> images;

    private Collection<RoomDto> rooms;

    private Collection<ReservationDto> availableReservations;

    private Collection<String> codeOfConduct;

    private Collection<UtilityDto> utilities;

    private OwnerDto vacationHomeOwner;
}
