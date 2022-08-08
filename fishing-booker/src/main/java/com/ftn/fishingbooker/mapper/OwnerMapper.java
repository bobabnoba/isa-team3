package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.OwnerDto;
import com.ftn.fishingbooker.model.User;

public class OwnerMapper {

    public static OwnerDto map(User owner) {
        OwnerDto homeOwnerDto = new OwnerDto();
        homeOwnerDto.setEmail(owner.getEmail());
        homeOwnerDto.setFirstName(owner.getFirstName());
        homeOwnerDto.setPhone(owner.getPhone());
        homeOwnerDto.setAddress(owner.getAddress());
        homeOwnerDto.setLastName(owner.getLastName());
        return homeOwnerDto;
    }

}
