package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.HomeOwner;
import com.ftn.fishingbooker.model.Room;
import com.ftn.fishingbooker.model.VacationHome;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class VacationHomeMapper {


    public static VacationHomeDto map(VacationHome home) {
        VacationHomeDto vacationHomeDto = new VacationHomeDto();
        vacationHomeDto.setName(home.getName());
        vacationHomeDto.setAddress(home.getAddress());
        vacationHomeDto.setDescription(home.getDescription());
        vacationHomeDto.setRating(home.getRating());
        vacationHomeDto.setGuestLimit(home.getGuestLimit());

        Collection<ImageDto> imagesDto = ImageMapper.map(home.getImages());
        vacationHomeDto.setImages(imagesDto);
        Collection<RoomDto> roomsDto = mapRooms(home.getRooms());
        vacationHomeDto.setRooms(roomsDto);

        Collection<ReservationDto> reservationsDto = ReservationMapper.map(home.getAvailableReservations());
        vacationHomeDto.setAvailableReservations(reservationsDto);
        vacationHomeDto.setCodeOfConduct(home.getCodeOfConduct());

        Collection<UtilityDto> utilitiesDto = UtilityMapper.map(home.getUtilities());
        vacationHomeDto.setUtilities(utilitiesDto);

        HomeOwnerDto homeOwnerDto = mapHomeOwner(home.getHomeOwner());
        vacationHomeDto.setVacationHomeOwner(homeOwnerDto);

        return vacationHomeDto;
    }

    private static HomeOwnerDto mapHomeOwner(HomeOwner homeOwner) {
        HomeOwnerDto homeOwnerDto = new HomeOwnerDto();
        homeOwnerDto.setEmail(homeOwner.getEmail());
        homeOwnerDto.setFirstName(homeOwner.getFirstName());
        homeOwnerDto.setPhone(homeOwner.getPhone());
        homeOwnerDto.setAddress(homeOwner.getAddress());
        homeOwnerDto.setLastName(homeOwner.getLastName());
        return homeOwnerDto;
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
}
