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
            userDto.add(new UserDto(u));
        }
        return userDto;
    }

    public List<User> mapToModel(List<UserDto> userList) {
        List<User> users = new ArrayList<>();
        return users;
    }
}
