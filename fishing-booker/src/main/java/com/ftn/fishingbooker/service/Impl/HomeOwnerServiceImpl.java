package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.*;
import com.ftn.fishingbooker.exception.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;


@Service
public class HomeOwnerServiceImpl implements HomeOwnerService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository repository;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    RegistrationRepository registrationRepository;

    @Override
    public User registerHomeOwner(HomeOwner homeOwner, String motivation)  {
        if (userService.isEmailRegistered(homeOwner.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        repository.save(homeOwner);

        Registration registration = new Registration(RegistrationType.VACATION_HOUSE_ADVERTISER, motivation, homeOwner.getEmail());
        registrationRepository.save(registration);

        return repository.findByEmail(homeOwner.getEmail());
    }
}
