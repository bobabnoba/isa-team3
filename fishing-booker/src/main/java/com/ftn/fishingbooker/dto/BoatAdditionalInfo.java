package com.ftn.fishingbooker.dto;

import lombok.*;
import com.ftn.fishingbooker.model.FishingEquipment;
import com.ftn.fishingbooker.model.Utility;

import java.util.*;

@Data
public class BoatAdditionalInfo {
    public Collection<FishingEquipment> fishingEquipment;
    public Collection<Utility> utilities;
    public Collection<String> navigationTypes;
}
