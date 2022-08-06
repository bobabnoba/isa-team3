package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.Address;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
public class VacationHomeDto {
    private Long id;

    private String name;

    private Address address;

    private String description;

    private Double rating;

    private Integer guestLimit;

    private Collection<ImageDto> images;

    private Collection<RoomDto> rooms;

    private Collection<ReservationDto> availableReservations;

    private Collection<String> codeOfConduct;

    private Collection<UtilityDto> utilities;

    private HomeOwnerDto vacationHomeOwner;
}
