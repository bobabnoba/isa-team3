package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.FilterDto;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.VacationHome;

import java.util.Collection;

public interface HomeService {
    Collection<VacationHome> getAll();

    VacationHome getById(Long id);

    Collection<VacationHome> filterAll(FilterDto filter);

    void makeReservation(Long homeId, Reservation reservation);
}
