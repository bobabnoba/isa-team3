package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.enumeration.*;
import lombok.Data;

@Data
public class OwnerRegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String city;
    private String country;
    private RegistrationType registrationType;
    private String motivation;
}
