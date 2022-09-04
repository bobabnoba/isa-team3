package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dao.*;
import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.enumeration.*;
import com.ftn.fishingbooker.model.Client;
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

    int getNoOfIncomingReservationsForAdventure(Long id);

    int getNoOfIncomingReservationsForBoat(Long id);

    int getNoOfIncomingReservationsForVacationHome(Long id);

    int getNoOfIncomingReservationsForUser(Long id, String role);


    Collection<BoatReservationInfo> getUpcomingReservationsForBoatOwner(Long id);

    Collection<Reservation> getPastReservationsForBoatOwner(Long id);

    Collection<BoatReservationInfo> getCurrentReservationsForBoatOwner(Long id);

    Reservation ownerMakeReservation(Client client, ReservationDto reservation);

    Reservation instructorMakeReservation(Client client, ReservationDto reservationDto, double durationInHours);

    Collection<ReservationCalendarInfo> getAllInstructorReservations(Long id);

    Collection<BoatReservationInfo> getUpcomingReservationsForHomeOwner(Long id);

    Collection<Reservation> getPastReservationsForHomeOwner(Long id);

    Collection<BoatReservationInfo> getCurrentReservationsForHomeOwner(Long id);

    Collection<Reservation> getCaptainReservationsForBoatOwner(Long id);

    Collection<Reservation> getReservationsForBoat(Long id);
    Collection<Reservation> getReservationsForHome(Long id);
    Collection<Reservation> getReservationsForAdventure(Long id);

}
