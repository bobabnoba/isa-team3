package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.Address;
import com.ftn.fishingbooker.model.Rule;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

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
    public Collection<String> imageUrls;

    private Collection<RoomDto> rooms;

    private Collection<ReservationDto> availableReservations;

    private Set<Rule> codeOfConduct;

    private Collection<UtilityDto> utilities;

    private OwnerDto vacationHomeOwner;

    public double cancelingPercentage;

    public String information;

    public Collection<SpecialOfferDto> specialOffers;
}
