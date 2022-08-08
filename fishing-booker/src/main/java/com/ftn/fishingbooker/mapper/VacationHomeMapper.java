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
}
