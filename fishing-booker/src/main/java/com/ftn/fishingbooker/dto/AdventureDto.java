package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.Rule;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
public class AdventureDto {

    public Long id;
    public String title;
    public AddressDto address;
    public String description;
    public Collection<String> images;
    public Set<Rule> codeOfConduct;
    public double pricePerDay;
    public double cancelingPercentage;
    public int maxNumberOfParticipants;
    public String instructorFirstName;
    public String instructorLastName;
    public String instructorBio;
    public Collection<FishingEquipmentDto> fishingEquipment;
    public Collection<UtilityDto> utilities;
    public double durationInHours;
}
