package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.Registration;

import javax.mail.*;

public interface AdminService {
    
    void respondToRegistrationRequest(Registration response) throws MessagingException;
}
