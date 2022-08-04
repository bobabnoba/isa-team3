package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {

     boolean isEmailRegistered(String email);

     UserDetails loadUserByUsername(String email);

     User createClient(RegisterDto registerRequest) throws MessagingException;

     User createAdmin(RegisterDto registerRequest);

    String enableUser(String email);

     User registerOwner(User owner, RegistrationType type, String motivation) throws MessagingException;

    User getByEmail(String email);

    User update(User user);

}
