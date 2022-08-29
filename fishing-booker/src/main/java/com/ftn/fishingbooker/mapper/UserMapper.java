package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.UserDto;
import com.ftn.fishingbooker.dto.UserInfoDto;
import com.ftn.fishingbooker.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAddress(user.getAddress());
        userDto.setPhone(user.getPhone());
        userDto.setActivated(user.isActivated());
        userDto.setBlocked(user.isBlocked());
        userDto.setBiography(user.getBiography());
        if(user.getRank() != null){
            userDto.setRank(UserRankMapper.toDto(user.getRank()));
        }
        userDto.setPoints(user.getPoints());

        return userDto;
    }

    public static User mapToEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setAddress(dto.getAddress());
        user.setPhone(dto.getPhone());
        user.setActivated(dto.isActivated());
        user.setBlocked(dto.isBlocked());
        user.setBiography(dto.getBiography());
        return user;
    }

    public static UserInfoDto mapToInfoDto(User user){
        UserInfoDto dto = new UserInfoDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().getName());
        if(user.getRank() != null) {
            dto.setRank(UserRankMapper.toDto(user.getRank()));
        }
        dto.setPoints(user.getPoints());
        return dto;
    }
}
