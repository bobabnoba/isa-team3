package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.mapper.UserMapper;
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
        List<User> users= userRepository.findAll();
        return userMapper.mapToDto(users);
    }

    public UserDto get(Long id) {
        User user = userRepository.getById(id);
        return userMapper.mapToDto(user);
    }

    public Long save(UserDto newUser) {
        User user = userMapper.mapToModel(newUser);
        userRepository.save(user);
        return user.getId();
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }


}
