package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.repository.ApplicationUserRepository;
import com.ftn.fishingbooker.service.UserService;
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
    private ApplicationUserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

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

    public UserDto getUserByName(String name) {
        User user = userRepository.getUserByName(name);
        return userMapper.mapToDto(user);
    }
//TODO: THIS
    @Override
    public boolean isEmailRegistered() {
        return true;
    }
    /**
     * UserDetailsService - Spring security requirements
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User was not found"));
        } else {
            return user;
        }
    }
}
