package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.mapper.RegistrationMapper;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.*;
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
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private ClientService clientService;
    private static String url = "<a href=\"http://localhost:4200/login\"> Login </a>";


    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return userMapper.mapToDto(users);
    }

    public UserDto get(Long id) {
        User user = userRepository.getById(id);
        return userMapper.mapToDto(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

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
        Client user = registrationMapper.mapToClient(registerDto);
        clientService.registerClient(user);
        return user;
    }
    @Override
    public User createAdmin(RegisterDto registerDto) {
        if (isEmailRegistered(registerDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        Admin user = registrationMapper.mapToAdmin(registerDto);
        //TODO:
        return user;
    }

    @Override
    public String enableUser(String email) {
        User u = userRepository.findByEmail(email);
        u.setActivated(true);
        return url;
    }


}
