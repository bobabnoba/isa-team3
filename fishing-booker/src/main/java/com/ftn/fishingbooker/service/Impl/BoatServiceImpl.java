package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.FilterDto;
import com.ftn.fishingbooker.model.Boat;
import com.ftn.fishingbooker.model.BoatAvailability;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.repository.BoatRepository;
import com.ftn.fishingbooker.service.BoatService;
import com.ftn.fishingbooker.service.DateService;
import com.ftn.fishingbooker.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class BoatServiceImpl implements BoatService {
    private final BoatRepository boatRepository;
    private final DateService dateService;
    private final ReservationService reservationService;

    @Override
    public Collection<Boat> getAll() {
        return boatRepository.getAll();
    }

    @Override
    public Collection<Boat> filterAll(FilterDto filter) {
        ArrayList<Boat> boatsList = (ArrayList<Boat>) boatRepository.getAll();
        ArrayList<Boat> filteredBoatsList = new ArrayList<>();

        for (Boat boat : boatsList) {

            if (boat.getGuestLimit() >= filter.getPeople()) {
                // Date should overlap with boat availability
                if (doPeriodsOverlap(filter.getStartDate(), filter.getEndDate(), boat.getAvailableTimePeriods())) {

                    Collection<Reservation> reservations = reservationService.getReservationForBoat(boat.getId());
                    boolean overlaps = dateOverlapsWithReservation(reservations, filter.getStartDate(), filter.getEndDate());

                    if (!overlaps) {
                        filteredBoatsList.add(boat);
                    }
                }
            }
        }
        return filteredBoatsList;
    }

    @Override
    public void makeReservation(Long boatId, Reservation reservation) {
        Boat boat = boatRepository.getById(boatId);
        boat.getReservations().add(reservation);
        boatRepository.save(boat);
    }

    private boolean dateOverlapsWithReservation(Collection<Reservation> reservations, Date startDate, Date endDate) {
        if (reservations == null) {
            return false;
        }
        for (Reservation reservation : reservations) {
            if (dateService.doPeriodsOverlap(reservation.getStartDate(), reservation.getEndDate(), startDate, endDate)) {
                return true;
            }
        }
        return false;
    }

    private boolean doPeriodsOverlap(Date startDate, Date endDate, Set<BoatAvailability> availableTimePeriods) {

        for (BoatAvailability period : availableTimePeriods) {
            if (dateService.doPeriodsOverlap(period.getStartDate(), period.getEndDate(), startDate, endDate)) {
                return true;
            }
        }
        return false;
    }
}
