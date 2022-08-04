package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.AdventureDto;
import com.ftn.fishingbooker.model.Adventure;

public class AdventureMapper {

    public static AdventureDto mapToDto(Adventure adventure) {
        AdventureDto dto = new AdventureDto();
        dto.setId(adventure.getId());
        dto.setTitle(adventure.getTitle());
        dto.setAddress(adventure.getAddress());
        dto.setDescription(adventure.getDescription());
        dto.setImages(adventure.getImages());
        dto.setPricePerDay(adventure.getPricePerDay());
        dto.setCancelingPercentage(adventure.getCancelingPercentage());
        dto.setMaxNumberOfParticipants(adventure.getMaxNumberOfParticipants());
        dto.setInstructorFirstName(adventure.getInstructor().getFirstName());
        dto.setInstructorBio(adventure.getInstructor().getBiography());
        dto.setInstructorLastName(adventure.getInstructor().getLastName());
        dto.setFishingEquipment(adventure.getFishingEquipment());
        dto.setAdditionalServices(adventure.getAdditionalServices());
        return dto;

    }
}
