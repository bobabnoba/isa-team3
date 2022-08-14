package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Reservation;

import java.util.Collection;

public interface ReservationService {

    Collection<Reservation> findAllForClient(Long clientId);

    Collection<Reservation> getReservationForVacationHome(Long homeId);

    Collection<Reservation> getReservationsForClient(Long clientId);
}