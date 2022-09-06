package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.AdventureAdditionalInfo;
import com.ftn.fishingbooker.dto.AdventureInfo;
import com.ftn.fishingbooker.dto.FilterDto;
import com.ftn.fishingbooker.exception.EntityNotFoundException;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.AdventureRepository;
import com.ftn.fishingbooker.repository.InstructorRepository;
import com.ftn.fishingbooker.service.AdventureService;
import com.ftn.fishingbooker.service.DateService;
import com.ftn.fishingbooker.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdventureServiceImpl implements AdventureService {

    private final AdventureRepository adventureRepository;
    private final InstructorRepository instructorRepository;
    private final DateService dateService;
    private final ReservationService reservationService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public Collection<Adventure> getAll() {
        return adventureRepository.findAll();
    }

    @Override
    public Collection<Adventure> getAllByInstructorEmail(String email) {
        Instructor instructor = instructorRepository.findByEmail(email);
        if (instructor == null) {
            throw new ResourceConflictException("Instructor with email " + email + " does not exist");
        }
        return adventureRepository.findAllByInstructorId(instructor.getId());
    }

    @Override
    @Transactional
    public void addImage(Long adventureId, String fileName) {
        Adventure found = adventureRepository.findById(adventureId)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));
        found.getImages().add(new Image(fileName));
        adventureRepository.save(found);
    }

    @Override
    @Transactional
    public Adventure addAdventure(Adventure adventure, String instructorEmail) {
        Instructor instructor = instructorRepository.findByEmail(instructorEmail);
        adventure.setInstructor(instructor);
        return adventureRepository.save(adventure);
    }

    @Override
    @Transactional(readOnly = true)
    public Adventure getById(Long id) {
        return adventureRepository.findById(id).orElseThrow(() -> new ResourceConflictException("Adventure not found"));
    }

    @Override
    @Transactional
    public Adventure updateAdventureInfo(Long id, AdventureInfo updated) {
        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        found.setTitle(updated.getTitle());
        found.setDescription(updated.getDescription());
        found.setPricePerDay(updated.getPrice());
        found.setMaxNumberOfParticipants(updated.getMaxParticipants());
        found.setCancelingPercentage(updated.getCancelingPercentage());
        found.setDurationInHours(updated.getDurationInHours());
        return adventureRepository.save(found);
    }

    @Override
    @Transactional
    public Adventure updateAdventureAdditionalInfo(Long id, AdventureAdditionalInfo updated) {

        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        found.setFishingEquipment(new HashSet<>(updated.getFishingEquipment()));
        found.setUtilities(new HashSet<>(updated.getUtilities()));
        return adventureRepository.save(found);
    }

    @Override
    @Transactional
    public Adventure updateAdventureRules(Long id, Collection<Rule> updated) {
        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        found.setCodeOfConduct(new HashSet<>(updated));
        return adventureRepository.save(found);
    }

    @Override
    @Transactional
    public Adventure updateAdventureAddress(Long id, Address updated) {
        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        found.setAddress(updated);
        return adventureRepository.save(found);
    }

    @Override
    public Collection<Long> getAllIdsByInstructorId(Long instructorId) {
        return adventureRepository.getAllIdsByInstructorId(instructorId);
    }

    @Override
    public Collection<Adventure> findAllByInstructorId(Long id) {
        return adventureRepository.findAllByInstructorId(id);
    }

    @Override
    public Adventure getAdventureForReservation(Long reservationId) {
        return adventureRepository.getAdventureForReservation(reservationId);
    }

    @Override
    public void updateAdventureRating(Long id, double adventureRating, double instructorRating) {
        Adventure adventure = adventureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Adventure not found"));
        adventure.setRating(adventureRating);
        adventure.getInstructor().setRating(instructorRating);
        adventureRepository.save(adventure);
    }

    @Override
    public int getNoOfIncomingReservations(Long id) {
        return reservationService.getNoOfIncomingReservationsForAdventure(id);
    }

    @Override
    public Collection<Adventure> findAllWithInstructor() {
        return adventureRepository.findAllWithInstructor();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));
        found.setDeleted(true);
        adventureRepository.save(found);
    }

    @Override
    public Adventure save(Adventure adventure) {
        return adventureRepository.save(adventure);
    }

    @Override
    public Collection<Adventure> filterAll(FilterDto filter) {
        ArrayList<Adventure> adventuresList = (ArrayList<Adventure>) adventureRepository.findAllWithInstructor();
        ArrayList<Adventure> filteredAdventuresList = new ArrayList<>();

        for (Adventure adventure : adventuresList) {

            if (adventure.getMaxNumberOfParticipants() >= filter.getPeople()) {
                // Date should overlap with vacation home availability
                if (inBetweenOrEqual(filter.getStartDate(), filter.getEndDate(), adventure.getInstructor().getAvailability())) {

                    Collection<Long> adventures = adventureRepository.getAllIdsByInstructorId(adventure.getInstructor().getId());
                    Collection<Reservation> reservations = reservationService.getReservationsForAdventures(adventures);
                    boolean overlaps = reservationService.dateOverlapsWithReservation(reservations, filter.getStartDate(), filter.getEndDate());

                    if (!overlaps) {
                        filteredAdventuresList.add(adventure);
                    }
                }
            }
        }
        return filteredAdventuresList;
    }

    private boolean inBetweenOrEqual(Date startDate, Date endDate, Set<InstructorAvailability> availableTimePeriods) {

        for (InstructorAvailability period : availableTimePeriods) {
            if (dateService.inBetweenOrEqual(period.getStartDate(), period.getEndDate(), startDate, endDate)) {
                return true;
            }
        }
        return false;
    }


    @Override
    @Transactional
    public Adventure makeReservation(Long adventureId, Reservation reservation) {
        Adventure adventure = adventureRepository.getWithReservations(adventureId);
        if (adventure == null) {
            adventure = adventureRepository.findById(adventureId)
                    .orElseThrow(() -> new ResourceConflictException("Adventure not found"));
            adventure.setReservations(new HashSet<>());
        }
        adventure.getReservations().add(reservation);
        return adventureRepository.save(adventure);
    }


    private boolean doPeriodsOverlap(Date startDate, Date endDate, Set<InstructorAvailability> availableTimePeriods) {

        for (InstructorAvailability period : availableTimePeriods) {
            if (dateService.doPeriodsOverlap(period.getStartDate(), period.getEndDate(), startDate, endDate)) {
                return true;
            }
        }
        return false;
    }


}

