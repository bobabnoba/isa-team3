package com.ftn.fishingbooker.dto;

import lombok.Data;

@Data
public class OwnerRegisterDto {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String address;
    public String phone;
    public String city;
    public Long zipCode;
    public String country;
    public String motivation;
}
