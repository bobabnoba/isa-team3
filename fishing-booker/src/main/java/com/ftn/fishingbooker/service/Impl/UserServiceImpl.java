package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.RegisterDto;
import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.mapper.RegistrationMapper;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.UserRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private BoatOwnerService boatOwnerService;
//    @Autowired
//    private InstructorService instructorService;
//    @Autowired
//    private HomeOwnerService homeOwnerService;


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
    public User createClient(RegisterDto registerDto) {
        if (isEmailRegistered(registerDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        Client user = registrationMapper.mapToClient(registerDto);
        clientService.registerClient(user);
        return user;
    }

    @Override
    public User createHomeOwner(RegisterDto registerDto) {
        if (isEmailRegistered(registerDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        HomeOwner user = registrationMapper.mapToHomeOwner(registerDto);
        //TODO:
        return user;
    }

    @Override
    public User createFishingInstructor(RegisterDto registerDto) {
        if (isEmailRegistered(registerDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        Instructor user = registrationMapper.mapToInstructor(registerDto);
        //TODO:
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
}
