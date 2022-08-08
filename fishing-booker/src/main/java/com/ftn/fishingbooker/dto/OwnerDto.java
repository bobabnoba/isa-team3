package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.Address;
import lombok.Data;

@Data
public class OwnerDto {
    private String firstName;

    private String lastName;

    private String email;

    private Address address;

    private String phone;

}
