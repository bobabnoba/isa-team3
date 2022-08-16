package com.ftn.fishingbooker.dto;

import lombok.Data;

@Data
public class RegisterDto {

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public AddressDto address;
    public String phone;
    public String city;
    public Long zipCode;
    public String country;


}
