package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.RegisterDto;
import com.ftn.fishingbooker.enumeration.RegistrationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.mapper.RegistrationMapper;
import com.ftn.fishingbooker.model.Admin;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.Registration;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.repository.UserRepository;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    RegistrationRepository registrationRepository;

    private static String url = "<a href=\"http://localhost:4200/login\"> Login </a>";


    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User was not found"));
        } else {
            return user;
        }
    }

    @Override
    public User createClient(RegisterDto registerDto) throws ResourceConflictException, MessagingException {
        if (isEmailRegistered(registerDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        Client user = RegistrationMapper.mapToClient(registerDto);
        clientService.registerClient(user);
        return user;
    }

    @Override
    public User createAdmin(RegisterDto registerDto) {
        if (isEmailRegistered(registerDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        Admin user = RegistrationMapper.mapToAdmin(registerDto);
        //TODO:
        return user;
    }

    @Override
    public String enableUser(String email) {
        User u = userRepository.findByEmail(email);
        u.setActivated(true);
        return url;
    }

    @Override
    public User registerOwner(User owner, RegistrationType type, String motivation) throws MessagingException {

        if (isEmailRegistered(owner.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        Registration registration = new Registration(type, motivation, owner.getEmail());
        registrationRepository.save(registration);


        return userRepository.save(owner);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User update(User user) {
        User dbUser = userRepository.findByEmail(user.getEmail());
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setPhone(user.getPhone());
        dbUser.setAddress(user.getAddress());
        dbUser.setBiography(user.getBiography());
        return userRepository.save(dbUser);
    }

    @Transactional
    @Override
    public void delete(String email) {
        User dbUser = userRepository.findByEmail(email);
        dbUser.setDeleted(true);
        userRepository.save(dbUser);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Collection<User> saveAll(Collection<User> users) {
        return userRepository.saveAll(users);
    }


}
