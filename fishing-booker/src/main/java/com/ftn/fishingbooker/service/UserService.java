package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {

    public boolean isEmailRegistered(String email);

    public UserDetails loadUserByUsername(String email);

    public User createClient(RegisterDto registerRequest) throws MessagingException;

    public User createAdmin(RegisterDto registerRequest);

    String enableUser(String email);

    public User registerOwner(User owner, RegistrationType type, String motivation) throws MessagingException;

}
