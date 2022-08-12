package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.FishingEquipment;
import com.ftn.fishingbooker.model.Utility;

import java.util.Collection;

public class AdventureAdditionalInfo {
    public Collection<FishingEquipment> fishingEquipment;
    public Collection<Utility> utilities;

    public Collection<FishingEquipment> getFishingEquipment() {
        return fishingEquipment;
    }

    public Collection<Utility> getUtilities() {
        return utilities;
    }
}
