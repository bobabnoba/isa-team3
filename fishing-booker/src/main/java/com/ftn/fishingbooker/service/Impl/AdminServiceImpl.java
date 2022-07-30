package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import javax.mail.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;

    @Override
    public void respondToRegistrationRequest(RegistrationResponseDto registrationResponseDto) throws MessagingException {
        Registration registration = registrationRepository.findByUserEmail(registrationResponseDto.userEmail);
        User user = userRepository.findByEmail(registration.getUserEmail());
        if(registrationResponseDto.isApproved()){
            user.setActivated(true);
            userRepository.save(user);
            registration.setApproved(true);
            registrationRepository.save(registration);
            String html = emailService.createAdminResponseEmail( "Your account is activated!", true);
            emailService.sendEmail(user.getEmail(),"Account activated!", html);
        }
        else{
            registration.setApproved(false);
            registration.setAdminResponse(registrationResponseDto.getResponse());
            registrationRepository.save(registration);
            //createAdminResponseEmail srediti da ima 2 template-a
            String html = emailService.createAdminResponseEmail( registrationResponseDto.getResponse(), false);
            emailService.sendEmail(user.getEmail(),"Account not  activated!", html);
        }
    }
}
