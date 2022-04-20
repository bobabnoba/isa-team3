package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.repository.UserRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RegistrationRepository registrationRepository;

    @Override
    public User registerInstructor(Instructor instructor, String motivation) {

        if (userService.isEmailRegistered(instructor.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        Registration registration = new Registration(RegistrationType.INSTRUCTOR_ADVERTISER, motivation, instructor.getEmail());
        registrationRepository.save(registration);

        userRepository.save(instructor);

        return userRepository.findByEmail(instructor.getEmail());
    }
}
