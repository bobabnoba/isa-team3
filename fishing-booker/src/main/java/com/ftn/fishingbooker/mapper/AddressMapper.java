package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.AddressDto;
import com.ftn.fishingbooker.model.Address;

public class AddressMapper {

    public static Address toEntity(AddressDto dto) {
        Address address = new Address();
        address.setId(dto.getId());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setStreet(dto.getStreet());
        address.setZipCode(dto.getZipCode());
        return address;
    }

    public static AddressDto toDto(Address address) {
        AddressDto dto = new AddressDto();
        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        dto.setStreet(address.getStreet());
        dto.setZipCode(address.getZipCode());
        return dto;
    }
}
