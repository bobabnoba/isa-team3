package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.BoatType;
import com.ftn.fishingbooker.enumeration.NavigationType;
import com.ftn.fishingbooker.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class BoatMapper {

    public static Collection<BoatDto> mapToDtoCollection(Collection<Boat> boats) {
        Collection<BoatDto> boatDtoList = new ArrayList<>();
        for (Boat boat : boats
        ) {
            BoatDto boatDto = mapToDto(boat);
            boatDtoList.add(boatDto);
        }
        return boatDtoList;
    }

    public static BoatDto mapToDto(Boat boat) {
        BoatDto dto = new BoatDto();
        dto.setId(boat.getId());
        dto.setName(boat.getName());
        dto.setRating(boat.getRating());
        dto.setType(boat.getType().toString());
        dto.setLength(boat.getLength());
        dto.setEngineCount(boat.getEngineCount());
        dto.setEnginePower(boat.getEnginePower());
        dto.setMaxSpeed(boat.getMaxSpeed());
        dto.setInformation(boat.getInformation());
        dto.setAddress(AddressMapper.toDto(boat.getAddress()));
        dto.setDescription(boat.getDescription());
        dto.setImages(boat.getImagePaths());
        dto.setPricePerDay(boat.getPricePerDay());
        dto.setCancelingPercentage(boat.getCancelingPercentage());
        dto.setGuestLimit(boat.getGuestLimit());
        dto.setOwnerFirstName(boat.getBoatOwner().getFirstName());
        dto.setOwnerLastName(boat.getBoatOwner().getLastName());
        dto.setOwnerEmail(boat.getBoatOwner().getEmail());
        dto.setFishingEquipment(FishingEquipmentMapper.toDto(boat.getFishingEquipment()));
        dto.setCodeOfConduct(boat.getCodeOfConduct());
        dto.setUtilities(UtilityMapper.map(boat.getUtilities()));
        dto.setNavigationTypes(boat.getNavigationType().stream().map(Enum::toString).collect(Collectors.toList()));
        return dto;
    }

    public static BoatDto mapToDtoWithAvailability(Boat boat) {
        BoatDto dto = new BoatDto();
        dto.setId(boat.getId());
        dto.setName(boat.getName());
        dto.setRating(boat.getRating());
        dto.setType(boat.getType().toString());
        dto.setLength(boat.getLength());
        dto.setEngineCount(boat.getEngineCount());
        dto.setEnginePower(boat.getEnginePower());
        dto.setMaxSpeed(boat.getMaxSpeed());
        dto.setInformation(boat.getInformation());
        dto.setAddress(AddressMapper.toDto(boat.getAddress()));
        dto.setDescription(boat.getDescription());
        dto.setImages(boat.getImagePaths());
        dto.setPricePerDay(boat.getPricePerDay());
        dto.setCancelingPercentage(boat.getCancelingPercentage());
        dto.setGuestLimit(boat.getGuestLimit());
        dto.setOwnerFirstName(boat.getBoatOwner().getFirstName());
        dto.setOwnerLastName(boat.getBoatOwner().getLastName());
        dto.setOwnerEmail(boat.getBoatOwner().getEmail());
        dto.setFishingEquipment(FishingEquipmentMapper.toDto(boat.getFishingEquipment()));
        dto.setCodeOfConduct(boat.getCodeOfConduct());
        dto.setUtilities(UtilityMapper.map(boat.getUtilities()));
        dto.setNavigationTypes(boat.getNavigationType().stream().map(Enum::toString).collect(Collectors.toList()));
        dto.setAvailability(boat.getAvailableTimePeriods().stream().map(BoatMapper::mapToAvailabilityDto).collect(Collectors.toSet()));
        var offers = SpecialOfferMapper.toDtoSet(boat.getSpecialOffers());
        dto.setSpecialOffers(offers.stream().filter(specialOffer -> specialOffer.getActiveTo().after(new Date())).collect(Collectors.toSet()));
        return dto;
    }

    public static Boat toEntity(NewBoatDto dto) {
        Boat boat = new Boat();
        boat.setType(BoatType.valueOf(dto.getType()));
        boat.setLength(dto.getLength());
        boat.setEngineCount(dto.getEngineCount());
        boat.setEnginePower(dto.getEnginePower());
        boat.setMaxSpeed(dto.getMaxSpeed());
        boat.setName(dto.getName());
        boat.setCodeOfConduct(new HashSet<>(dto.getCodeOfConduct()));
        boat.setGuestLimit(dto.getGuestLimit());
        boat.setDescription(dto.getDescription());
        boat.setPricePerDay(dto.getPricePerDay());
        boat.setCancelingPercentage(dto.getCancelingPercentage());
        boat.setFishingEquipment(FishingEquipmentMapper.toEntity(dto.getFishingEquipment()));
        boat.setUtilities(UtilityMapper.toEntitySet(dto.getUtilities()));
        boat.setAddress(AddressMapper.toEntity(dto.getAddress()));
        boat.setInformation(dto.getInformation());
        boat.setNavigationType(dto.getNavigationTypes().stream().map(s -> mapNavigation(s)).collect(Collectors.toSet()));
        return boat;
    }

    public static NavigationType mapNavigation(String s) {
        if (Objects.equals(s, "GPS")) {
            return NavigationType.GPS;
        } else if (Objects.equals(s, "RADAR")) {
            return NavigationType.RADAR;
        } else if (Objects.equals(s, "VHF_RADIO")) {
            return NavigationType.VHF_RADIO;
        } else if (Objects.equals(s, "FISHFINDER")) {
            return NavigationType.FISHFINDER;
        }
        return NavigationType.RADAR;

    }

    public static BoatAvailabilityDto mapToAvailabilityDto(BoatAvailability availability) {
        BoatAvailabilityDto dto = new BoatAvailabilityDto();
        dto.setId(availability.getId());
        dto.setStartDate(availability.getStartDate());
        dto.setEndDate(availability.getEndDate());
        return dto;
    }

    public static List<BoatAvailabilityDto> mapToListOfAvailabilityDtos(Set<BoatAvailability> availabilities) {
        return availabilities.stream().map(BoatMapper::mapToAvailabilityDto).collect(Collectors.toList());
    }

    public static BoatAvailability mapToBoatAvailabilityEntity(BoatAvailabilityRequestDto availability) {
        BoatAvailability entity = new BoatAvailability();
        entity.setStartDate(availability.startDate);
        entity.setEndDate(availability.endDate);
        return entity;
    }

    public static BoatInfo mapToDtoInfo(Boat boat) {
        BoatInfo dto = new BoatInfo();

        dto.setName(boat.getName());
        dto.setType(boat.getType().toString());
        dto.setLength(boat.getLength());
        dto.setEngineCount(boat.getEngineCount());
        dto.setEnginePower(boat.getEnginePower());
        dto.setMaxSpeed(boat.getMaxSpeed());
        dto.setInformation(boat.getInformation());
        dto.setDescription(boat.getDescription());
        dto.setPricePerDay(boat.getPricePerDay());
        dto.setCancelingPercentage(boat.getCancelingPercentage());
        dto.setGuestLimit(boat.getGuestLimit());
        return dto;
    }

    public static BoatAvailabilityDto mapToAvailabilityDtoFromOwnerAvailability(BoatOwnerAvailability availability) {
        BoatAvailabilityDto dto = new BoatAvailabilityDto();
        dto.setId(availability.getId());
        dto.setStartDate(availability.getStartDate());
        dto.setEndDate(availability.getEndDate());
        return dto;
    }
}
