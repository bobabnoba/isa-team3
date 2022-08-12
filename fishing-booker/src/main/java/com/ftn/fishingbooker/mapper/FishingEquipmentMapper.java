package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.FishingEquipmentDto;
import com.ftn.fishingbooker.model.FishingEquipment;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class FishingEquipmentMapper {

    public static Set<FishingEquipment> toEntity(Collection<FishingEquipmentDto> dtos){
        Set<FishingEquipment> entities = new HashSet<>();
        dtos.forEach(dto -> entities.add(new FishingEquipment(dto.getId(), dto.getName())));
        return entities;
    }

    public static Collection<FishingEquipmentDto> toDto(Set<FishingEquipment> fishingEquipment) {
        Collection<FishingEquipmentDto> dtos = new ArrayList<>();
        fishingEquipment.forEach(fe -> dtos.add(new FishingEquipmentDto(fe.getId(), fe.getName())));
        return dtos;
    }
}

