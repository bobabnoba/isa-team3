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
            ReservationDto reservationDto = map(res);
            reservationsDto.add(reservationDto);
        }
        return reservationsDto;
    }

    public static ReservationDto map(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setGuests(reservation.getGuests());
        reservationDto.setId(reservation.getId());
        reservationDto.setIsCancelled(reservation.getIsCancelled());
        reservationDto.setType(reservation.getType());
        reservationDto.setPrice(reservation.getPrice());
        reservationDto.setEndDate(reservation.getEndDate());
        reservationDto.setStartDate(reservation.getStartDate());
        if (reservation.getReport() != null) {
            reservationDto.setReport(ReportMapper.toDto(reservation.getReport()));
        }
        if (reservation.getClientReview() != null) {
            reservationDto.setClientReview(ReviewMapper.map(reservation.getClientReview()));
        }
        return reservationDto;
    }

    public static Reservation map(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setGuests(reservationDto.getGuests());
        reservation.setId(reservationDto.getId());
        reservation.setIsCancelled(false);
        reservation.setType(reservationDto.getType());
        reservation.setPrice(reservationDto.getPrice());
        reservation.setEndDate(reservationDto.getEndDate());
        reservation.setStartDate(reservationDto.getStartDate());
        return reservation;
    }


}
