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

    BoatAvailability addAvailabilityPeriod(BoatAvailability mapToBoatAvailabilityEntity, Long boatId);
}
