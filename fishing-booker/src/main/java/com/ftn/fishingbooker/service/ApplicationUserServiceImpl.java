package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.BoatOwner;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.HomeOwner;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ApplicationUserServiceImpl implements ApplicationUserService {

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

    public Long saveClient(UserDto newUser) {
        Client client = userMapper.mapToClient(newUser);
        userRepository.save(client);
        return client.getId();
    }

    public Long saveHomeOwner(UserDto newUser) {
        HomeOwner client = userMapper.mapToHomeOwner(newUser);
        userRepository.save(client);
        return client.getId();
    }

    public Long saveBoatOwner(UserDto newUser) {
        BoatOwner client = userMapper.mapToBoatOwner(newUser);
        userRepository.save(client);
        return client.getId();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto getUserByName(String name) {
        User user = userRepository.getUserByName(name);
        return userMapper.mapToDto(user);
    }
}
