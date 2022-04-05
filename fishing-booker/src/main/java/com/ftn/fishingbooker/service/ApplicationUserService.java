package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.UserDto;

import java.util.List;

public interface ApplicationUserService {

    List<UserDto> getAll();

    UserDto get(Long id);

    Long saveClient(UserDto newUser);

    Long saveHomeOwner(UserDto newUser);

    Long saveBoatOwner(UserDto newUser);

    void delete(Long id);

}
