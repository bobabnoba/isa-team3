package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.*;
import com.ftn.fishingbooker.exception.*;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.*;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final HomeRepository vacationHomeRepository;
    private final ReservationService reservationService;
    private final DateService dateService;
    private final HomeOwnerService homeOwnerService;
    private final EarningsService earningsService;
    private final HomeOwnerRepository homeOwnerRepository;


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

    @Override
    public Collection<VacationHome> getAllByOwner(String email) {
        HomeOwner owner = homeOwnerRepository.findByEmail(email);
        if (owner == null) {
            throw new ResourceConflictException("Home owner with email " + email + " does not exist");
        }
        return vacationHomeRepository.findAllByOwnerId(owner.getId());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        VacationHome found = vacationHomeRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVU VIKENDICU!
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForVacationHome(id);
        if ( !(noOfFutureRes > 0)){
            found.setDeleted(true);
            vacationHomeRepository.save(found);
        }
        //TODO: dodati povratnu poruku
    }

    @Override
    @Transactional
    public VacationHome addHome(VacationHome home, String ownerEmail) {
        HomeOwner owner = homeOwnerRepository.findByEmail(ownerEmail);
        home.setHomeOwner(owner);
        return vacationHomeRepository.save(home);

    }

    @Override
    @Transactional
    public VacationHome updateHomeInfo(Long id, HomeInfoDto updated) {
        VacationHome found = vacationHomeRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForVacationHome(id);
        if ( !(noOfFutureRes > 0)) {
            found.setName(updated.getName());
            found.setInformation(updated.getInformation());
            found.setDescription(updated.getDescription());
            found.setPricePerDay(updated.getPricePerDay());
            found.setCancelingPercentage(updated.getCancelingPercentage());
            found.setGuestLimit(updated.getGuestLimit());
        }
        return vacationHomeRepository.save(found);
    }

    @Override
    @Transactional
    public VacationHome updateHomeAdditionalInfo(Long id, HomeAdditionalInfo updated) {

        VacationHome found = vacationHomeRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForVacationHome(id);
        if ( !(noOfFutureRes > 0)) {
            found.setUtilities(new HashSet<>(updated.getUtilities()));
            found.setRooms(new HashSet<>(updated.getRooms().stream().map(VacationHomeMapper::mapToRoom).collect(Collectors.toSet())));
        }
        return vacationHomeRepository.save(found);
    }

    @Override
    @Transactional
    public VacationHome updateHomeRules(Long id, Collection<Rule> updated) {
        VacationHome found = vacationHomeRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForVacationHome(id);
        if ( !(noOfFutureRes > 0)) {
            found.setCodeOfConduct(new HashSet<>(updated));
        }
        return vacationHomeRepository.save(found);
    }

    @Override
    @Transactional
    public VacationHome updateHomeAddress(Long id, Address updated) {
        VacationHome found = vacationHomeRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        //TODO: DODATI PROVJERU DA LI IMA REZERVACIJA ZA OVAJ BROD!
        //ako ima ne moze se editovati ako nema edituj ga
        var noOfFutureRes = reservationService.getNoOfIncomingReservationsForVacationHome(id);
        if ( !(noOfFutureRes > 0)) {
            found.setAddress(updated);
        }
        return vacationHomeRepository.save(found);
    }

    @Override
    @Transactional
    public void addImage(Long boatId, String fileName) {
        VacationHome found = vacationHomeRepository.findById(boatId)
                .orElseThrow(() -> new ResourceConflictException("Vacation home not found"));
        found.getImages().add(new Image(fileName));
        vacationHomeRepository.save(found);
    }


}
