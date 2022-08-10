package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.AdventureDto;
import com.ftn.fishingbooker.dto.NewAdventureDto;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.model.FishingEquipment;
import com.ftn.fishingbooker.model.Utility;

import java.util.HashSet;
import java.util.stream.Collectors;

public class AdventureMapper {

    public static AdventureDto mapToDto(Adventure adventure) {
        AdventureDto dto = new AdventureDto();
        dto.setId(adventure.getId());
        dto.setTitle(adventure.getTitle());
        dto.setAddress(adventure.getAddress());
        dto.setDescription(adventure.getDescription());
        dto.setImages(adventure.getImagePaths());
        dto.setPricePerDay(adventure.getPricePerDay());
        dto.setCancelingPercentage(adventure.getCancelingPercentage());
        dto.setMaxNumberOfParticipants(adventure.getMaxNumberOfParticipants());
        dto.setInstructorFirstName(adventure.getInstructor().getFirstName());
        dto.setInstructorBio(adventure.getInstructor().getBiography());
        dto.setInstructorLastName(adventure.getInstructor().getLastName());
        dto.setFishingEquipment(adventure.getFishingEquipment());
        dto.setUtilities(UtilityMapper.map(adventure.getUtilities()));
        return dto;
    }

    public static Adventure toEntity(NewAdventureDto dto) {
        Adventure adventure = new Adventure();
        adventure.setTitle(dto.getTitle());
        adventure.setCodeOfConduct(new HashSet<>(dto.getCodeOfConduct()));
        adventure.setDescription(dto.getDescription());
        adventure.setPricePerDay(dto.getPricePerDay());
        adventure.setCancelingPercentage(dto.getCancelingPercentage());
        adventure.setFishingEquipment(FishingEquipmentMapper.toEntity(dto.getFishingEquipment()));
        adventure.setUtilities(
                dto.getUtilities().stream()
                        .map(uDto -> new Utility(uDto.getName(), uDto.getPrice()))
                        .collect(Collectors.toSet()));
        adventure.setAddress(AddressMapper.toEntity(dto.getAddress()));

        return adventure;
    }
}
