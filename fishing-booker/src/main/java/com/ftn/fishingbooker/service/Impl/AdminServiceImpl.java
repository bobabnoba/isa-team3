package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import javax.mail.*;

@Service
public class AdminServiceImpl implements AdminService {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public AdminServiceImpl(RegistrationRepository registrationRepository, UserRepository userRepository,
                            EmailService emailService) {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public void respondToRegistrationRequest(Registration registration) throws MessagingException {
        User user = userRepository.findByEmail(registration.getUserEmail());
        if(registration.isApproved()){
            user.setActivated(true);
            userRepository.save(user);
            registration.setApproved(true);
            registrationRepository.save(registration);
            String html = emailService.createAdminResponseEmail( "Your account is activated!", true);
            emailService.sendEmail(user.getEmail(),"Account activated!", html);
        }
        else{
            registration.setApproved(false);
            registration.setAdminResponse(registration.getAdminResponse());
            registrationRepository.save(registration);
            //TODO: createAdminResponseEmail srediti da ima 2 template-a
            String html = emailService.createAdminResponseEmail( registration.getAdminResponse(), false);
            emailService.sendEmail(user.getEmail(),"Account not  activated!", html);
        }
    }
}
