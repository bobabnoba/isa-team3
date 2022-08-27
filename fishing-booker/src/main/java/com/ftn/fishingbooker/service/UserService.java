package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.InvalidPasswordException;
import com.ftn.fishingbooker.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.mail.MessagingException;
import java.util.Collection;

public interface UserService {

    boolean isEmailRegistered(String email);

    UserDetails loadUserByUsername(String email);

    User createClient(RegisterDto registerRequest) throws MessagingException;

    String enableUser(String email);

    User registerOwner(User owner, RegistrationType type, String motivation) throws MessagingException;

    User getByEmail(String email);

    User update(User user);

    void delete(String email);

    User save(User user);

    Collection<User> getAll();

    Collection<User> saveAll(Collection<User> users);

    User getUserById(Long id);

    void changePassword(String email, PasswordChangeDto request);
}
