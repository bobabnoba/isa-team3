package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.User;

public class UserDto {

    public Long id;

    public String firstName;

    public UserDto(User u) {

        this.id = u.getId();
        this.firstName = u.getFirstName();
    }
}
