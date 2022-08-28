package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.FilterDto;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.model.VacationHomeAvailability;
import com.ftn.fishingbooker.repository.HomeRepository;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final HomeRepository vacationHomeRepository;
    private final ReservationService reservationService;
    private final DateService dateService;
    private final HomeOwnerService homeOwnerService;
    private final EarningsService earningsService;


    @Override
    public Collection<VacationHome> getAll() {
        return vacationHomeRepository.getAll();
    }

    @Override
    @Transactional
    public VacationHome getById(Long id) {
        return vacationHomeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Home with id " + id + " does not exist"));
    }

    @Override
    public Collection<VacationHome> filterAll(FilterDto filter) {
        ArrayList<VacationHome> homeList = (ArrayList<VacationHome>) vacationHomeRepository.getAll();
        ArrayList<VacationHome> filteredHomeList = new ArrayList<>();

        for (VacationHome home : homeList) {

            if (home.getGuestLimit() >= filter.getPeople()) {
                // Date should overlap with vacation home availability
                if (doPeriodsOverlap(filter.getStartDate(), filter.getEndDate(), home.getAvailability())) {

                    Collection<Reservation> reservations = reservationService.getReservationForVacationHome(home.getId());
                    boolean overlaps = reservationService.dateOverlapsWithReservation(reservations, filter.getStartDate(), filter.getEndDate());

                    if (!overlaps) {
                        filteredHomeList.add(home);
                    }
                }
            }
        }
        return filteredHomeList;
    }
    private boolean doPeriodsOverlap(Date startDate, Date endDate, Set<VacationHomeAvailability> availableTimePeriods) {

        for (VacationHomeAvailability period : availableTimePeriods) {
            if (dateService.doPeriodsOverlap(period.getStartDate(), period.getEndDate(), startDate, endDate)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void makeReservation(Long homeId, Reservation reservation) {
        VacationHome home = vacationHomeRepository.getById(homeId);
        home.getReservations().add(reservation);
        vacationHomeRepository.save(home);
        homeOwnerService.updatePoints(home.getHomeOwner(), reservation.getPrice());
        earningsService.saveEarnings(reservation, home.getHomeOwner().getEmail(), home.getHomeOwner().getRank());

    }

    @Override
    public VacationHome getVacationHomeForReservation(Long reservationId) {
        return vacationHomeRepository.getVacationHomeForReservation(reservationId);
    }


}
