package com.ftn.fishingbooker.dto;

import lombok.Data;

@Data
public class AdminDto {

    public Long id;

    public String firstName;

    public String lastName;

    public String email;

    public String password;

    public AddressDto address;

    public String phone;

    public boolean firstLogin;

}
