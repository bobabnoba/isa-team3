package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.*;
import com.ftn.fishingbooker.exception.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.registration.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.*;

public class BoatOwnerServiceImpl implements BoatOwnerService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository repository;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    RegistrationRepository registrationRepository;

    @Override
    public User registerBoatOwner(BoatOwner boatOwner, String motivation) {
        if (userService.isEmailRegistered(boatOwner.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        repository.save(boatOwner);

        Registration registration = new Registration(RegistrationType.VACATION_BOAT_ADVERTISER, motivation, boatOwner);
        registrationRepository.save(registration);

        return repository.findByEmail(boatOwner.getEmail());
    }
}
