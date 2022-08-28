package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dao.ReservationInfo;
import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.ClientReview;
import com.ftn.fishingbooker.model.Reservation;

import java.util.Collection;
import java.util.Date;

public interface ReservationService {

    Collection<Reservation> findAllForClient(Long clientId);

    Collection<Reservation> getReservationForVacationHome(Long homeId);

    Collection<Reservation> getReservationsForClient(Long clientId);

    Reservation makeReservation(Client client, ReservationDto reservation);

    boolean dateOverlapsWithReservation(Collection<Reservation> reservations, Date startDate, Date endDate);

    Collection<Reservation> getReservationForBoat(Long id);

    Reservation makeReservation(Client client, ReservationDto reservationDto, double durationInHours);

    Reservation makeSpecialOfferReservation(Client client, ReservationDto reservationDto);

    Collection<Reservation> getReservationsForAdventures(Collection<Long> id);

    Collection<ReservationInfo> getUpcomingReservationsForInstructor(Long id);

    Collection<Reservation> getPastReservationsForInstructor(Long id);

    Reservation getReservationById(Long id);

    Reservation save(Reservation reservation);

    Reservation getOngoingReservationForInstructor(Long id);

    Reservation leaveReview(Long reservationId, ClientReview clientReview);

}
