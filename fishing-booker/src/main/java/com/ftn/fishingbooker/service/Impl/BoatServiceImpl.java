package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.*;
import com.ftn.fishingbooker.exception.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BoatServiceImpl implements BoatService {
    private final BoatRepository boatRepository;
    private final DateService dateService;
    private final ReservationService reservationService;
    private final BoatOwnerService boatOwnerService;
    private final BoatOwnerRepository boatOwnerRepository;
    private final EarningsService earningsService;

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
        boatOwnerService.updatePoints(boat.getBoatOwner(), reservation.getPrice());
        earningsService.saveApplicationEarnings(reservation, boat.getBoatOwner().getEmail(), boat.getBoatOwner().getRank());
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

    @Override
    public Collection<Boat> getAllByOwner(String email) {
        BoatOwner owner = boatOwnerRepository.findByEmail(email);
        if (owner == null) {
            throw new ResourceConflictException("Instructor with email " + email + " does not exist");
        }
        return boatRepository.findAllByOwnerId(owner.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Boat getById(Long id) {
        return boatRepository.findById(id).orElseThrow(() -> new ResourceConflictException("Boat not found"));

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Boat found = boatRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Boat not found"));
        found.setDeleted(true);
        boatRepository.save(found);
    }

    @Override
    @Transactional
    public Boat addBoat(Boat boat, String ownerEmail) {
        BoatOwner owner = boatOwnerRepository.findByEmail(ownerEmail);
        boat.setBoatOwner(owner);
        return boatRepository.save(boat);
    }

    @Override
    @Transactional
    public void addImage(Long boatId, String fileName) {
        Boat found = boatRepository.findById(boatId)
                .orElseThrow(() -> new ResourceConflictException("Boat not found"));
        found.getImages().add(new Image(fileName));
        boatRepository.save(found);
    }

    @Override
    public Boat save(Boat boat) {
        return boatRepository.save(boat);
    }

    @Override
    @Transactional
    public Boat updateBoatInfo(Long id, BoatInfo updated) {
        Boat found = boatRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Boat not found"));

        found.setName(updated.getName());
        found.setType(BoatType.valueOf(updated.getType()));
        found.setLength(updated.getLength());
        found.setEngineCount(updated.getEngineCount());
        found.setEnginePower(updated.getEnginePower());
        found.setMaxSpeed(updated.getMaxSpeed());
        found.setInformation(updated.getInformation());
        found.setDescription(updated.getDescription());
        found.setPricePerDay(updated.getPricePerDay());
        found.setCancelingPercentage(updated.getCancelingPercentage());
        found.setGuestLimit(updated.getGuestLimit());

        return boatRepository.save(found);
    }

    @Override
    @Transactional
    public Boat updateBoatAdditionalInfo(Long id, BoatAdditionalInfo updated) {

        Boat found = boatRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Boat not found"));

        found.setFishingEquipment(new HashSet<>(updated.getFishingEquipment()));
        found.setUtilities(new HashSet<>(updated.getUtilities()));
        found.setNavigationType(updated.getNavigationTypes().stream().map(NavigationType::valueOf).collect(Collectors.toSet()));

        return boatRepository.save(found);
    }

    @Override
    @Transactional
    public Boat updateBoatRules(Long id, Collection<Rule> updated) {
        Boat found = boatRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Boat not found"));

        found.setCodeOfConduct(new HashSet<>(updated));
        return boatRepository.save(found);
    }

    @Override
    @Transactional
    public Boat updateBoatAddress(Long id, Address updated) {
        Boat found = boatRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Boat not found"));

        found.setAddress(updated);
        return boatRepository.save(found);
    }


}
