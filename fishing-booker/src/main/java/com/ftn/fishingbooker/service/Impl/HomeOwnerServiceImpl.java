package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.exception.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.registration.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.*;

import javax.mail.*;

public class HomeOwnerServiceImpl implements HomeOwnerService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository repository;
    @Autowired
    private RegistrationService registrationService;

    @Override
    public User registerHomeOwner(HomeOwner homeOwner, String motivation)  {
        if (userService.isEmailRegistered(homeOwner.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        User user = repository.save(homeOwner);
        //String registrationToken = registrationService.generateRegistrationToken(homeOwner.getEmail(), homeOwner.getRole().getName());
        //registrationService.sendRegistrationEmail(registrationToken, homeOwner.getEmail());

        return user;
    }
}
