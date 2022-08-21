package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.AdventureDto;
import com.ftn.fishingbooker.dto.NewAdventureDto;
import com.ftn.fishingbooker.model.Adventure;

import java.util.HashSet;

public class AdventureMapper {

    public static AdventureDto mapToDto(Adventure adventure) {
        AdventureDto dto = new AdventureDto();
        dto.setId(adventure.getId());
        dto.setTitle(adventure.getTitle());
        dto.setAddress(AddressMapper.toDto(adventure.getAddress()));
        dto.setDescription(adventure.getDescription());
        dto.setImages(adventure.getImagePaths());
        dto.setPricePerDay(adventure.getPricePerDay());
        dto.setCancelingPercentage(adventure.getCancelingPercentage());
        dto.setMaxNumberOfParticipants(adventure.getMaxNumberOfParticipants());
        dto.setInstructorFirstName(adventure.getInstructor().getFirstName());
        dto.setInstructorBio(adventure.getInstructor().getBiography());
        dto.setInstructorLastName(adventure.getInstructor().getLastName());
        dto.setFishingEquipment(FishingEquipmentMapper.toDto(adventure.getFishingEquipment()));
        dto.setCodeOfConduct(adventure.getCodeOfConduct());
        dto.setUtilities(UtilityMapper.map(adventure.getUtilities()));
        dto.setDurationInHours(adventure.getDurationInHours());
        dto.setSpecialOffers(SpecialOfferMapper.toDtoSet(adventure.getSpecialOffers()));
        dto.setDurationInHours(adventure.getDurationInHours());
        return dto;
    }

    public static Adventure toEntity(NewAdventureDto dto) {
        Adventure adventure = new Adventure();
        adventure.setTitle(dto.getTitle());
        adventure.setCodeOfConduct(new HashSet<>(dto.getCodeOfConduct()));
        adventure.setMaxNumberOfParticipants(dto.getMaxNumberOfParticipants());
        adventure.setDescription(dto.getDescription());
        adventure.setPricePerDay(dto.getPricePerDay());
        adventure.setCancelingPercentage(dto.getCancelingPercentage());
        adventure.setFishingEquipment(FishingEquipmentMapper.toEntity(dto.getFishingEquipment()));
        adventure.setUtilities(UtilityMapper.toEntitySet(dto.getUtilities()));
        adventure.setAddress(AddressMapper.toEntity(dto.getAddress()));
        adventure.setDurationInHours(dto.getDurationInHours());
        return adventure;
    }

    public static Adventure mapToEntity(AdventureDto dto) {
        Adventure adventure = new Adventure();
        adventure.setId(dto.getId());
        adventure.setTitle(dto.getTitle());
        adventure.setCodeOfConduct(new HashSet<>(dto.getCodeOfConduct()));
        adventure.setMaxNumberOfParticipants(dto.getMaxNumberOfParticipants());
        adventure.setDescription(dto.getDescription());
        adventure.setPricePerDay(dto.getPricePerDay());
        adventure.setCancelingPercentage(dto.getCancelingPercentage());
        adventure.setFishingEquipment(FishingEquipmentMapper.toEntity(dto.getFishingEquipment()));
        adventure.setUtilities(UtilityMapper.toEntitySet(dto.getUtilities()));
        adventure.setAddress(AddressMapper.toEntity(dto.getAddress()));

        return adventure;
    }
}
