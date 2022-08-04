package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.Registration;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.repository.UserRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.stereotype.*;

import javax.mail.MessagingException;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;


    public InstructorServiceImpl(UserService userService, UserRepository userRepository, RegistrationRepository registrationRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public User register(Instructor instructor, String motivation) throws ResourceConflictException {

        if (userService.isEmailRegistered(instructor.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        Registration registration = new Registration(RegistrationType.INSTRUCTOR_ADVERTISER, motivation, instructor.getEmail());
        registrationRepository.save(registration);

        return userRepository.save(instructor);
    }
}
