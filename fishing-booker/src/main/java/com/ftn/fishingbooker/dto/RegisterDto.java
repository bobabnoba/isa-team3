package com.ftn.fishingbooker.dto;

import lombok.Data;

@Data
public class RegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String city;
    private String country;
}
