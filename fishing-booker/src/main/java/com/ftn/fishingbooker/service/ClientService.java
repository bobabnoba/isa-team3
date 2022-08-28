package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.Reservation;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

public interface ClientService {

    void registerClient(Client client) throws MessagingException;

    boolean hasOverlappingReservation(String email, Date startDate, Date endDate);

    Client getClientByEmail(String userEmail);

    void updatePoints(Client client, double reservationPrice);

    void addPenalty(String email);

    List<Reservation> getUpcomingReservations(String email);

    boolean cancelUpcomingReservation(Long reservationId, String userEmail);

    List<Reservation> getPastReservations(String userEmail, ReservationType vacationHome);

}
