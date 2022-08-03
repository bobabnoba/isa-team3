package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.home.VacationHomeReservation;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ReservationMapper {

    public static Set<ReservationDto> map(Set<VacationHomeReservation> reservations) {

        Set<ReservationDto> reservationsDto = new HashSet<>();
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
