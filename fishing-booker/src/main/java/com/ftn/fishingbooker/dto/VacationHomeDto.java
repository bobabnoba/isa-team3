package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.Image;
import com.ftn.fishingbooker.model.home.Room;
import com.ftn.fishingbooker.model.home.VacationHomeReservation;
import com.ftn.fishingbooker.model.home.VacationHomeUtility;
import lombok.Data;

import java.util.Set;

@Data
public class VacationHomeDto {
    private String name;

    private String address;

    private String description;

    private Double rating;

    private Integer guestLimit;

    private String information;

    private Set<ImageDto> images;

    private Set<RoomDto> rooms;

    private Set<ReservationDto> availableReservations;

    private Set<String> codeOfConduct;

    private Set<UtilityDto> utilities;

    private VacationHomeOwnerDto vacationHomeOwner;

}
