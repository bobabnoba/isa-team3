package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.OwnerDto;
import com.ftn.fishingbooker.model.User;

public class OwnerMapper {

    public static OwnerDto map(User owner) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setEmail(owner.getEmail());
        ownerDto.setFirstName(owner.getFirstName());
        ownerDto.setPhone(owner.getPhone());
        ownerDto.setAddress(owner.getAddress());
        ownerDto.setLastName(owner.getLastName());
        return ownerDto;
    }

}
