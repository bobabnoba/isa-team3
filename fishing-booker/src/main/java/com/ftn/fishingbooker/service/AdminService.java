package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Admin;
import com.ftn.fishingbooker.model.Registration;

import javax.mail.*;

public interface AdminService {
    
    void respondToRegistrationRequest(Registration response) throws MessagingException;

    Admin addNewAdmin(Admin admin);

    void updateFirstLogin(Admin admin);

    boolean isFirstLogin(String email);
}
