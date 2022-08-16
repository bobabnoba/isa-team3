package com.ftn.fishingbooker.dto;

import lombok.Data;

@Data
public class AddressDto {

    public Long id;
    public String street;
    public String city;
    public String country;
    public Long zipCode;

}
