package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.Address;
import lombok.Data;

@Data
public class ClientDto {
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public Address address;
    public String phone;
    public String biography;
    public UserRankDto rank;
    public double points;
    public int penalties;
}
