package com.ftn.fishingbooker.dto;
import com.ftn.fishingbooker.model.Rule;
import lombok.Data;

import java.util.List;

@Data
public class NewAdventureDto {

    public String title;
    public AddressDto address;
    public String description;
    public List<Rule> codeOfConduct;
    public double pricePerDay;
    public double cancelingPercentage;
    public int maxNumberOfParticipants;
    public String instructorEmail;
    public List<FishingEquipmentDto> fishingEquipment;
    public List<UtilityDto> utilities;
}
