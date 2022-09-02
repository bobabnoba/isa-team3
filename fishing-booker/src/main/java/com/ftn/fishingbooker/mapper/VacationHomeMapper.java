package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.*;
import com.ftn.fishingbooker.model.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.*;

@Component
public class VacationHomeMapper {

    public static Collection<VacationHomeDto> map(Collection<VacationHome> homes) {
        Collection<VacationHomeDto> homesDto = new ArrayList<>();
        for (VacationHome home :
                homes) {
            VacationHomeDto dto = map(home);
            homesDto.add(dto);
        }
        return homesDto;

    }

    public static VacationHomeDto map(VacationHome home) {
        VacationHomeDto vacationHomeDto = new VacationHomeDto();
        vacationHomeDto.setId(home.getId());
        vacationHomeDto.setName(home.getName());
        vacationHomeDto.setAddress(home.getAddress());
        vacationHomeDto.setDescription(home.getDescription());
        vacationHomeDto.setRating(home.getRating());
        vacationHomeDto.setGuestLimit(home.getGuestLimit());
        vacationHomeDto.setPricePerDay(home.getPricePerDay());
        Collection<ImageDto> imagesDto = ImageMapper.map(home.getImages());
        vacationHomeDto.setImages(imagesDto);
        Collection<RoomDto> roomsDto = mapRooms(home.getRooms());
        vacationHomeDto.setRooms(roomsDto);

        Collection<ReservationDto> reservationsDto = ReservationMapper.map(home.getReservations());
        vacationHomeDto.setAvailableReservations(reservationsDto);
        vacationHomeDto.setCodeOfConduct(home.getCodeOfConduct());

        Collection<UtilityDto> utilitiesDto = UtilityMapper.map(home.getUtilities());
        vacationHomeDto.setUtilities(utilitiesDto);

        OwnerDto homeOwnerDto = OwnerMapper.map(home.getHomeOwner());
        vacationHomeDto.setVacationHomeOwner(homeOwnerDto);

        vacationHomeDto.setCancelingPercentage(home.getCancelingPercentage());
        vacationHomeDto.setInformation(home.getInformation());
        return vacationHomeDto;
    }


    private static Collection<RoomDto> mapRooms(Collection<Room> rooms) {
        Collection<RoomDto> roomsDto = new ArrayList<>();
        for (Room room :
                rooms) {
            RoomDto dto = new RoomDto();
            dto.setNumberOfBeds(room.getBedNumber());
            roomsDto.add(dto);

        }
        return roomsDto;
    }

    public static VacationHome toEntity(NewHomeDto dto) {
        VacationHome home = new VacationHome();
        home.setName(dto.getName());
        home.setCodeOfConduct(new HashSet<>(dto.getCodeOfConduct()));
        home.setGuestLimit(dto.getGuestLimit());
        home.setDescription(dto.getDescription());
        home.setPricePerDay(dto.getPricePerDay());
        home.setCancelingPercentage(dto.getCancelingPercentage());
        home.setUtilities(UtilityMapper.toEntitySet(dto.getUtilities()));
        home.setAddress(AddressMapper.toEntity(dto.getAddress()));
        home.setInformation(dto.getInformation());
        home.setRooms(dto.getRooms().stream().map(VacationHomeMapper::mapToRoom).collect(Collectors.toSet()));
        return home;
    }

    public static Room mapToRoom(RoomDto dto){
        Room room = new Room();
        room.setBedNumber(dto.getNumberOfBeds());
        return room;
    }



    public static VacationHomeDto mapToHomeOwnerDto(VacationHome home) {
        VacationHomeDto vacationHomeDto = new VacationHomeDto();
        vacationHomeDto.setId(home.getId());
        vacationHomeDto.setName(home.getName());
        vacationHomeDto.setAddress(home.getAddress());
        vacationHomeDto.setDescription(home.getDescription());
        vacationHomeDto.setRating(home.getRating());
        vacationHomeDto.setGuestLimit(home.getGuestLimit());
        vacationHomeDto.setPricePerDay(home.getPricePerDay());
        vacationHomeDto.setImageUrls(home.getImagePaths());
        vacationHomeDto.setRooms(home.getRooms().stream().map(VacationHomeMapper::mapToRoomDto).collect(Collectors.toSet()));
        vacationHomeDto.setCodeOfConduct(home.getCodeOfConduct());
        Collection<UtilityDto> utilitiesDto = UtilityMapper.map(home.getUtilities());
        vacationHomeDto.setUtilities(utilitiesDto);
        OwnerDto homeOwnerDto = OwnerMapper.map(home.getHomeOwner());
        vacationHomeDto.setVacationHomeOwner(homeOwnerDto);
        vacationHomeDto.setCancelingPercentage(home.getCancelingPercentage());
        vacationHomeDto.setInformation(home.getInformation());
        var offers = SpecialOfferMapper.toDtoSet(home.getSpecialOffers());
        vacationHomeDto.setSpecialOffers(offers.stream().filter(specialOffer -> specialOffer.getActiveTo().after(new Date())).collect(Collectors.toSet()));
        vacationHomeDto.setAvailability(home.getAvailability().stream().map(VacationHomeMapper::mapToAvailabilityDto).collect(Collectors.toList()));
        return vacationHomeDto;
    }

    public static RoomDto mapToRoomDto(Room room){
        RoomDto dto = new RoomDto();
        dto.setNumberOfBeds(room.getBedNumber());
        return dto;
    }

    public static VacationHomeAvailability mapToHomeAvailabilityEntity(HomeAvailabilityRequestDto availability){
        VacationHomeAvailability entity = new VacationHomeAvailability();
        entity.setStartDate(availability.startDate);
        entity.setEndDate(availability.endDate);
        return entity;
    }

    public static HomeAvailabilityDto mapToAvailabilityDto( VacationHomeAvailability availability){
        HomeAvailabilityDto dto  = new HomeAvailabilityDto();
        dto.setId(availability.getId());
        dto.setStartDate(availability.getStartDate());
        dto.setEndDate(availability.getEndDate());
        return dto;
    }

    public static HomeInfoDto mapToDtoInfo(VacationHome home) {
        HomeInfoDto vacationHomeDto = new HomeInfoDto();
        vacationHomeDto.setName(home.getName());
        vacationHomeDto.setDescription(home.getDescription());
        vacationHomeDto.setGuestLimit(home.getGuestLimit());
        vacationHomeDto.setPricePerDay(home.getPricePerDay());
        Collection<UtilityDto> utilitiesDto = UtilityMapper.map(home.getUtilities());
        OwnerDto homeOwnerDto = OwnerMapper.map(home.getHomeOwner());
        vacationHomeDto.setCancelingPercentage(home.getCancelingPercentage());
        vacationHomeDto.setInformation(home.getInformation());
        return vacationHomeDto;
    }
}
