package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.*;
import lombok.*;

import java.util.*;

@Data
public class BoatDto {

    public Long id;
    public String name;
    public double rating;
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
    public Collection<String> images;
    public Set<Rule> codeOfConduct;
    public double cancelingPercentage;
    public String ownerFirstName;
    public String ownerLastName;
    public String ownerEmail;
    public Collection<FishingEquipmentDto> fishingEquipment;
    public Collection<UtilityDto> utilities;
    public Collection<String> navigationTypes;
    public Collection<BoatAvailabilityDto> availability;
    //public Collection<SpecialOfferDto> specialOffers;
}
