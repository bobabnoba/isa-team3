package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.FilterDto;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.repository.HomeRepository;
import com.ftn.fishingbooker.service.DateService;
import com.ftn.fishingbooker.service.HomeService;
import com.ftn.fishingbooker.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final HomeRepository vacationHomeRepository;
    private final ReservationService reservationService;
    private final DateService dateService;

    @Override
    public Collection<VacationHome> getAll() {
        return vacationHomeRepository.getAll();
    }

    @Override
    public VacationHome getById(Long id) {
        return vacationHomeRepository.getById(id);
    }

    @Override
    public Collection<VacationHome> filterAll(FilterDto filter) {
        ArrayList<VacationHome> homeList = (ArrayList<VacationHome>) vacationHomeRepository.getAll();
        ArrayList<VacationHome> filteredHomeList = new ArrayList<>();

        for (VacationHome home : homeList) {

            if (home.getGuestLimit() >= filter.getPeople()) {
                // Date should overlap with vacation home availability
                if (dateService.doPeriodsOverlap(filter.getStartDate(), filter.getEndDate(), home.getAvailableTimePeriods())) {

                    Collection<Reservation> reservations = reservationService.getReservationForVacationHome(home.getId());
                    boolean overlaps = dateOverlapsWithReservation(reservations, filter.getStartDate(), filter.getEndDate());

                    if (!overlaps) {
                        filteredHomeList.add(home);
                    }
                }
            }
        }
        return filteredHomeList;
    }

    @Override
    public void makeReservation(Long homeId, Reservation reservation) {
        VacationHome home = vacationHomeRepository.getById(homeId);
        home.getReservations().add(reservation);
        vacationHomeRepository.save(home);
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

}
