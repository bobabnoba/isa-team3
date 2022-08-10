package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.FishingEquipmentDto;
import com.ftn.fishingbooker.model.FishingEquipment;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FishingEquipmentMapper {

    public static Set<FishingEquipment> toEntity(Collection<FishingEquipmentDto> dtos){
        Set<FishingEquipment> entities = new HashSet<>();
        dtos.forEach(dto -> entities.add(new FishingEquipment(dto.getId(), dto.getName())));
        return entities;
    }
}

