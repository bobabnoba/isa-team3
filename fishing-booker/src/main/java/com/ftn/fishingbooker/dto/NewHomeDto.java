package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.*;
import lombok.*;

import java.util.*;

@Data
public class NewHomeDto {
    public String name;
    public double pricePerDay;
    public AddressDto address;
    public String description;
    public String information;
    public int guestLimit;
    public Set<Rule> codeOfConduct;
    public double cancelingPercentage;
    public String ownerEmail;
    public Collection<UtilityDto> utilities;
    public Collection<RoomDto> rooms;

}
