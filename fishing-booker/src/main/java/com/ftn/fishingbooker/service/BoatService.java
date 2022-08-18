package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.FilterDto;
import com.ftn.fishingbooker.model.Boat;
import com.ftn.fishingbooker.model.Reservation;

import java.util.Collection;

public interface BoatService {
    Collection<Boat> getAll();

    Collection<Boat> filterAll(FilterDto filter);

    void makeReservation(Long boatId, Reservation reservation);
}
