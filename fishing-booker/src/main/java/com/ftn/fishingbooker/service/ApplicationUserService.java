package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
