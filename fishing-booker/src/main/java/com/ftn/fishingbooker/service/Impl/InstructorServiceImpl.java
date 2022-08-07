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
import org.springframework.stereotype.*;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final UserService userService;
    // TODO: add new userService method that will save both user and registration ...
    private final RegistrationRepository registrationRepository;
    private final InstructorAvailabilityService availabilityService;
    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(UserService userService, RegistrationRepository registrationRepository, InstructorAvailabilityService instructorAvailabilityService, InstructorRepository instructorRepository) {
        this.userService = userService;
        this.registrationRepository = registrationRepository;
        this.availabilityService = instructorAvailabilityService;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public User register(Instructor instructor, String motivation) throws ResourceConflictException {

        if (userService.isEmailRegistered(instructor.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        Registration registration = new Registration(RegistrationType.INSTRUCTOR_ADVERTISER, motivation, instructor.getEmail());
        registrationRepository.save(registration);

        return userService.save(instructor);
    }

    @Override
    public InstructorAvailability addAvailabilityPeriod(InstructorAvailability availability, String email) {
        InstructorAvailability saved = availabilityService.save(availability);
        Instructor instructor = instructorRepository.findByEmail(email);
        instructor.getAvailability().add(saved);
        instructorRepository.save(instructor);
        return saved;
    }

    @Override
    public Instructor getWithAvailability(String email) {
        return instructorRepository.findByEmail(email);
    }


}
