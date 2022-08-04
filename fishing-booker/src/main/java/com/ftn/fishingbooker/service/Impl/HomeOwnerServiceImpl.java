package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.HomeOwner;
import com.ftn.fishingbooker.model.Registration;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.repository.UserRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.stereotype.Service;

@Service
public class HomeOwnerServiceImpl implements HomeOwnerService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;

    public HomeOwnerServiceImpl(UserService userService, UserRepository userRepository, RegistrationRepository registrationRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public User register(HomeOwner homeOwner, String motivation) throws ResourceConflictException {
        if (userService.isEmailRegistered(homeOwner.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        Registration registration = new Registration(RegistrationType.VACATION_HOUSE_ADVERTISER, motivation, homeOwner.getEmail());
        registrationRepository.save(registration);

        return userRepository.save(homeOwner);
    }
}
