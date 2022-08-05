package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.model.Reservation;

import java.util.ArrayList;
import java.util.Collection;

public class ReservationMapper {
    public static Collection<ReservationDto> map(Collection<Reservation> reservations) {

        Collection<ReservationDto> reservationsDto = new ArrayList<>();
        for (Reservation res : reservations
        ) {
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setMaxGuests(res.getMaxGuests());
            reservationDto.setPrice(res.getPrice());
            reservationDto.setEndDate(res.getEndDate());
            reservationDto.setStartDate(res.getStartDate());

            reservationsDto.add(reservationDto);
        }
        return reservationsDto;
    }
}
