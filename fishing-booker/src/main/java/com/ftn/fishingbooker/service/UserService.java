package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public String getAddress(Integer userId){
        return userRepository.getAddress(userId);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
