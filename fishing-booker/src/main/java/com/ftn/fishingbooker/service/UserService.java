package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.RegisterDto;
import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    public List<UserDto> getAll();

    public UserDto get(Long id);

    public void delete(Long id);

    public boolean isEmailRegistered(String email);

    public UserDetails loadUserByUsername(String email);

    public User createClient(RegisterDto registerRequest);

    public User createHomeOwner(RegisterDto registerRequest);

    public User createFishingInstructor(RegisterDto registerRequest);

    public User createAdmin(RegisterDto registerRequest);
}
