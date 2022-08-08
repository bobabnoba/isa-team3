package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.InstructorAvailability;
import com.ftn.fishingbooker.model.Registration;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.repository.InstructorRepository;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.*;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final UserService userService;
    // TODO: add new userService method that will save both user and registration ...
    private final RegistrationRepository registrationRepository;
    private final InstructorAvailabilityService availabilityService;
    private final InstructorRepository instructorRepository;

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

        //instructor.setAvailability(availabilities);
        instructorRepository.save(instructor);

        //TODO: delete all availabilities that overlapped and got replaced // availabilityService

        return added;
    }

    private List<InstructorAvailability> checkForOverlapping(InstructorAvailability availability, List<InstructorAvailability> availabilities) {

        List<InstructorAvailability> retVal = new ArrayList<>();
        for (InstructorAvailability a : availabilities) {
            if (!a.getId().equals(availability.getId()) && (isBetween(availability.getStartDate(), a) || isBetween(availability.getEndDate(), a))) {
                Date newStartDate = a.getStartDate();
                Date newEndDate = a.getEndDate();
                availabilities.remove(a);
                a = calculateNew(newStartDate, newEndDate, availability);
            }
            else if (availability.getStartDate().before(a.getStartDate()) &&
                    availability.getEndDate().after(a.getEndDate())){
                a.setStartDate(availability.getStartDate());
                a.setEndDate(availability.getEndDate());
            }
            retVal.add(a);
        }

        return retVal;
    }

    private InstructorAvailability calculateNew(Date newStartDate, Date newEndDate, InstructorAvailability availability) {
        if ( newStartDate.before(availability.getStartDate())) {
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

}
