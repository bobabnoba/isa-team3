package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.repository.UserRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import javax.mail.MessagingException;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    private RegistrationService registrationService;

    @Override
    public User registerInstructor(Instructor instructor, String motivation) throws MessagingException {

        if (userService.isEmailRegistered(instructor.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        Registration registration = new Registration(RegistrationType.INSTRUCTOR_ADVERTISER, motivation, instructor.getEmail());
        registrationRepository.save(registration);

        String registrationToken = registrationService.generateRegistrationToken(instructor.getEmail(), instructor.getRole().getName());
        registrationService.sendRegistrationEmail(registrationToken, instructor.getEmail());

        userRepository.save(instructor);

        return userRepository.findByEmail(instructor.getEmail());
    }
}
