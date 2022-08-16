package com.ftn.fishingbooker.dto;

public class FishingEquipmentDto {
    public Long id;
    public String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FishingEquipmentDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FishingEquipmentDto(){}
}
