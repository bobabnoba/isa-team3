package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.home.Room;
import com.ftn.fishingbooker.model.home.VacationHome;
import com.ftn.fishingbooker.model.home.VacationHomeOwner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class VacationHomeMapper {

    public static VacationHomeDto map(VacationHome home) {
        VacationHomeDto vacationHomeDto = new VacationHomeDto();
        vacationHomeDto.setName(home.getName());
        vacationHomeDto.setAddress(home.getAddress());
        vacationHomeDto.setDescription(home.getDescription());
        vacationHomeDto.setRating(home.getRating());
        vacationHomeDto.setGuestLimit(home.getGuestLimit());
        vacationHomeDto.setInformation(home.getInformation());

        Set<ImageDto> imagesDto= ImageMapper.map(home.getImages());
        vacationHomeDto.setImages(imagesDto);
        Set<RoomDto> roomsDto = mapRooms(home.getRooms());
        vacationHomeDto.setRooms(roomsDto);

        Set<ReservationDto> reservationsDto = ReservationMapper.map(home.getAvailableReservations());
        vacationHomeDto.setAvailableReservations(reservationsDto);
        vacationHomeDto.setCodeOfConduct(home.getCodeOfConduct());

        Set<UtilityDto> utilitiesDto = UtilityMapper.map(home.getUtilities());
        vacationHomeDto.setUtilities(utilitiesDto);

        VacationHomeOwnerDto homeOwnerDto = mapHomeOwner(home.getVacationHomeOwner());
        vacationHomeDto.setVacationHomeOwner(homeOwnerDto);

        return vacationHomeDto;

    }

    private static VacationHomeOwnerDto mapHomeOwner(VacationHomeOwner vacationHomeOwner) {
        VacationHomeOwnerDto homeOwnerDto = new VacationHomeOwnerDto();
        homeOwnerDto.setEmail(vacationHomeOwner.getEmail());
        homeOwnerDto.setFirstName(vacationHomeOwner.getFirstName());
        homeOwnerDto.setPhone(vacationHomeOwner.getPhone());
        homeOwnerDto.setAddress(vacationHomeOwner.getAddress());
        homeOwnerDto.setLastName(vacationHomeOwner.getLastName());
        return homeOwnerDto;
    }

    private static Set<RoomDto> mapRooms(Set<Room> rooms) {
        Set<RoomDto> roomsDto = new HashSet<>();
        for (Room room :
                rooms) {
            RoomDto dto = new RoomDto();
            dto.setNumberOfBeds(room.getNumberOfBeds());
            roomsDto.add(dto);

        }
        return roomsDto;
    }
}
