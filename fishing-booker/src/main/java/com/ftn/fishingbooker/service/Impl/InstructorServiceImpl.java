package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.dao.ReservationInfo;
import com.ftn.fishingbooker.repository.InstructorRepository;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final UserService userService;
    // TODO: add new userService method that will save both user and registration ...
    private final RegistrationRepository registrationRepository;
    private final InstructorAvailabilityService availabilityService;
    private final InstructorRepository instructorRepository;
    private final AdventureService adventureService;
    private final UserRankService userRankService;
    private final ReservationService reservationService;

    @Transactional
    @Override
    public User register(Instructor instructor, String motivation) throws ResourceConflictException {

        if (userService.isEmailRegistered(instructor.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        Registration registration = new Registration(RegistrationType.INSTRUCTOR_ADVERTISER, motivation, instructor.getEmail());
        registrationRepository.save(registration);

        return userService.save(instructor);
    }

    @Transactional
    @Override
    public InstructorAvailability addAvailabilityPeriod(InstructorAvailability availability, String email) {

        Instructor instructor = instructorRepository.findByEmail(email);
        List<InstructorAvailability> availabilities = new ArrayList<>(instructor.getAvailability());
        InstructorAvailability added = availabilityService.save(availability);
        availabilities.add(added);

        instructor.setAvailability(new HashSet<>(checkForOverlapping(added, availabilities)));

        instructorRepository.save(instructor);

        //TODO: delete all availabilities that overlapped and got replaced // availabilityService

        return added;
    }

    @Override
    @Transactional
    public void updateAvailability(InstructorAvailability periodToDelete, String email) {
        Instructor instructor = instructorRepository.findByEmail(email);
        List<InstructorAvailability> availabilities = new ArrayList<>();
        for (var a : new ArrayList<>(instructor.getAvailability())) {
            if (periodToDelete.getStartDate().after(a.getStartDate())
                    && periodToDelete.getEndDate().before(a.getEndDate())) {
                InstructorAvailability parted = new InstructorAvailability(periodToDelete.getEndDate(), a.getEndDate());
                a.setEndDate(periodToDelete.getStartDate());
                availabilities.add(availabilityService.save(parted));
            }
            availabilities.add(a);
        }
        instructor.setAvailability(new HashSet<>(availabilities));
        instructorRepository.save(instructor);
    }

    @Override
    public void makeReservation(Long adventureId, Reservation reservation) {
        Adventure adventure = adventureService.getById(adventureId);
    }

    @Override
    public void updatePoints(Instructor instructor, double reservationPrice) {

        instructor.setPoints(instructor.getPoints() + reservationPrice * instructor.getRank().getReservationPercentage() / 100);

        userRankService.getLoyaltyProgram().forEach(rank -> {
            if (rank.getName().contains("ADVERTISER") && rank.getMinPoints() < instructor.getPoints()) {
                instructor.setRank(rank);
            }
        });
        instructorRepository.save(instructor);
    }

    @Override
    @Transactional
    public Collection<ReservationInfo> getUpcomingReservationsForInstructor(String email) {
        Instructor instructor = instructorRepository.findByEmail(email);
        //Collection<Long> adventureIds = adventureService.getAllIdsByInstructorId(instructor.getId());
        return reservationService.getUpcomingReservationsForInstructor(instructor.getId());
    }

    @Override
    public Collection<Reservation> getPastReservationsForInstructor(String email) {
        Instructor instructor = instructorRepository.findByEmail(email);
        return reservationService.getPastReservationsForInstructor(instructor.getId());
    }

    @Override
    public Instructor findByEmail(String email) {
        return instructorRepository.findByEmail(email);
    }

    @Override
    public Instructor findById(Long instructorId) {
        return instructorRepository.findById(instructorId).orElseThrow(() -> new IllegalArgumentException("Instructor not found"));
    }

    @Override
    public boolean hasOverlappingReservation(String email, Date from, Date to) {
        Instructor instructor = instructorRepository.findByEmail(email);

        Collection<Long> adventures = adventureService.getAllIdsByInstructorId(instructor.getId());
        Collection<Reservation> reservations = reservationService.getReservationsForAdventures(adventures);

        return reservationService.dateOverlapsWithReservation(reservations, from, to);
    }

    @Override
    public Reservation getOngoingReservationForInstructor(String email) {
        Instructor instructor = instructorRepository.findByEmail(email);
        return reservationService.getOngoingReservationForInstructor(instructor.getId());
    }

    private List<InstructorAvailability> checkForOverlapping(InstructorAvailability availability, List<InstructorAvailability> availabilities) {

        List<InstructorAvailability> retVal = new ArrayList<>();
        for (InstructorAvailability a : availabilities) {
            if (!a.getId().equals(availability.getId()) && (isBetween(availability.getStartDate(), a) || isBetween(availability.getEndDate(), a))) {
                Date newStartDate = a.getStartDate();
                Date newEndDate = a.getEndDate();
                a = calculateNew(newStartDate, newEndDate, availability);
            } else if (availability.getStartDate().before(a.getStartDate()) &&
                    availability.getEndDate().after(a.getEndDate())) {
                a.setStartDate(availability.getStartDate());
                a.setEndDate(availability.getEndDate());
            }
            retVal.add(a);
        }

        return retVal;
    }

    private InstructorAvailability calculateNew(Date newStartDate, Date newEndDate, InstructorAvailability availability) {
        if (newStartDate.before(availability.getStartDate())) {
            availability.setStartDate(newStartDate);
        }
        if (newEndDate.after(availability.getEndDate())) {
            availability.setEndDate(newEndDate);
        }
        return availability;
    }

    private boolean isBetween(Date newAvailabilityDate, InstructorAvailability availability) {
        return (newAvailabilityDate.after(availability.getStartDate())
                && newAvailabilityDate.before(availability.getEndDate())) ||
                newAvailabilityDate.equals(availability.getStartDate())
                || newAvailabilityDate.equals(availability.getEndDate());
    }

    @Override
    public Instructor getWithAvailability(String email) {
        return instructorRepository.findByEmail(email);
    }

    @Override
    public Collection<Instructor> getAll() {
        return instructorRepository.getAll();
    }

    @Override
    public boolean checkAvailability(Date from, Date to, String instructorEmail) {
        boolean isAvailable = false;
        Instructor instructor = instructorRepository.findByEmail(instructorEmail);
        List<InstructorAvailability> availabilityPeriods = new ArrayList<>(instructor.getAvailability());

        for (InstructorAvailability period : availabilityPeriods) {
            if (from.after(period.getStartDate()) && to.before(period.getEndDate())) {
                isAvailable = true;
            }
        }
        return isAvailable;
    }

}
