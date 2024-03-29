package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.model.*;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ClientService {

    void registerClient(Client client) throws MessagingException;

    boolean hasOverlappingReservation(String email, Date startDate, Date endDate);

    Client getClientByEmail(String userEmail);

    void updatePoints(Client client, double reservationPrice);

    void addPenalty(String email);

    List<Reservation> getUpcomingReservations(String email);

    Reservation cancelUpcomingReservation(Long reservationId, String userEmail);

    List<Reservation> getPastReservations(String userEmail, ReservationType vacationHome);

    Collection<Boat> getBoatSubscription(String clientEmail);

    Collection<VacationHome> getVacationHomeSubscription(String clientEmail);

    Collection<Instructor> getInstructorSubscription(String clientEmail);

    void save(Client client);

    boolean isClientSubscribed(String clientEmail, String entityType, Long entityId);

    Collection<Client> getAll();

    void emailSubscribers(User owner, String entityType)  throws MessagingException ;
}
