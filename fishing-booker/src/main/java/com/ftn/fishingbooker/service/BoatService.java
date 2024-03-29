package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.*;

import java.util.*;

public interface BoatService {

    Collection<Boat> getAll();

    Collection<Boat> filterAll(FilterDto filter);

    void makeReservation(Long boatId, Reservation reservation);

    Collection<Boat> getAllByOwner(String email);

    Boat getById(Long id);

    void deleteById(Long id);

    Boat addBoat(Boat boat, String ownerEmail);

    void addImage(Long boatId, String fileName);

    Boat save(Boat boat);

    Boat updateBoatInfo(Long id, BoatInfo updated);

    Boat updateBoatAdditionalInfo(Long id, BoatAdditionalInfo updated);

    Boat updateBoatAddress(Long id, Address updated);

    Boat updateBoatRules(Long id, Collection<Rule> updated);

    Boat getBoatForReservation(Long reservationId);

    Collection<BoatAvailability> addAvailabilityPeriod(BoatAvailability mapToBoatAvailabilityEntity, Long boatId);

    Collection<BoatAvailability> updateAvailability(Date reservationStartDate, Date reservationEndDate, Long id);

    boolean checkAvailability(Date from, Date to, Long boatId);

    int getNoOfIncomingReservations(Long id);

    Boolean checkIfReservationOverlapsAvailability(BoatAvailability mapToBoatAvailabilityEntity, Long boatId);

    void updateBoatRating(Long rentalId, double boatRating);

    Boat findLockedById(Long id);
}
