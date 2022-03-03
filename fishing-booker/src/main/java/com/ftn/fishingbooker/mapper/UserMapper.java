package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public List<UserDto> mapToDto(List<User> userList) {
        List<UserDto> userDto = new ArrayList<>();
        for (User u : userList) {
            userDto.add(mapToDto(u));
        }
        return userDto;
    }

    public List<User> mapToModel(List<UserDto> userList) {
        List<User> users = new ArrayList<>();
        for (UserDto u : userList) {
            users.add(mapToModel(u));
        }
        return users;
    }

    public User mapToModel(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());
        user.setCity(userDto.getCity());
        user.setCountry(userDto.getCountry());
        user.setActivated(userDto.isActivated());
        user.setBlocked(userDto.isBlocked());


        return user;
    }
    public UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAddress(user.getAddress());
        userDto.setPhone(user.getPhone());
        userDto.setCity(user.getCity());
        userDto.setCountry(user.getCountry());
        userDto.setActivated(user.isActivated());
        userDto.setBlocked(user.isBlocked());

        return userDto;
    }


}
