package com.ftn.fishingbooker.dto;

import lombok.Data;

@Data
public class UserDto {

    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String address;
    public String phone;
    public String city;
    private String zipCode;
    public String country;
    public boolean activated;
    public boolean blocked;
}
