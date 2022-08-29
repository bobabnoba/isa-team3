package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Admin;
import com.ftn.fishingbooker.model.Registration;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;
    private final AdminRepository adminRepository;

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
            String html = emailService.createAdminResponseEmail( registration.getAdminResponse(), false);
            emailService.sendEmail(user.getEmail(),"Account not  activated!", html);
        }
    }

    @Override
    public Admin addNewAdmin(Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        admin.setRole(roleService.findByName("ROLE_ADMIN"));
        return adminRepository.save(admin);
    }

    @Override
    public void updateFirstLogin(Admin admin) {
        admin.setFirstLogin(false);
        adminRepository.save(admin);
    }

    @Override
    public boolean isFirstLogin(String email) {
        return adminRepository.findByEmail(email).isFirstLogin();
    }

    @Override
    public Admin getHeadAdmin() {
        return adminRepository.findByHeadAdmin(true);
    }
}
