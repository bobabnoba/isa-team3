package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.AdditionalService;
import com.ftn.fishingbooker.model.Address;
import com.ftn.fishingbooker.model.FishingEquipment;
import lombok.Data;

import java.util.Set;

@Data
public class AdventureDto {

    public Long id;
    public String title;
    public Address address;
    public String description;
    public Set<String> images;
    public String codeOfConduct;
    public double pricePerDay;
    public double cancelingPercentage;
    public int maxNumberOfParticipants;
    public String instructorFirstName;
    public String instructorLastName;
    public String instructorBio;
    public Set<FishingEquipment> fishingEquipment;
    public Set<AdditionalService> additionalServices;
}
