package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    UserDto get(Long id);

    void delete(Long id);

    boolean isEmailRegistered();
}
