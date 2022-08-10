package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.AddressDto;
import com.ftn.fishingbooker.model.Address;

public class AddressMapper {

    public static Address toEntity(AddressDto dto) {
        Address address = new Address();
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setStreet(dto.getStreet());
        address.setZipCode(dto.getZipCode());
        return address;
    }
}
