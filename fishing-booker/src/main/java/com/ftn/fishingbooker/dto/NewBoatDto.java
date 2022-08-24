package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.*;
import lombok.*;

import java.util.*;

@Data
public class NewBoatDto {
    public String name;
    public String type;
    public float length;
    public int engineCount;
    public int enginePower;
    public float maxSpeed;
    public double pricePerDay;
    public AddressDto address;
    public String description;
    public String information;
    public int guestLimit;
    public Set<Rule> codeOfConduct;
    public double cancelingPercentage;
    public String ownerEmail;
    public Collection<FishingEquipmentDto> fishingEquipment;
    public Collection<UtilityDto> utilities;
    public Collection<String> navigationTypes;
}
