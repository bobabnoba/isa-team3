package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.*;

public class BoatOwnerMapper {

    public static BoatOwnerDto toDto(BoatOwner boatOwner){
        BoatOwnerDto dto = new BoatOwnerDto();
        dto.setUser(UserMapper.mapToDto(boatOwner));
        dto.setAvailability(boatOwner.getAvailability());
        return dto;
    }
}
