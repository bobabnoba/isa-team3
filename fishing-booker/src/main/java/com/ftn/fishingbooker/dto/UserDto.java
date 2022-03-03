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
    public String country;
    public boolean isActivated;
    public boolean isBlocked;

}
