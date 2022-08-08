package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.InstructorAvailability;

import java.util.Collection;

public class InstructorDto {
    public  UserDto user;
    public Collection<InstructorAvailability> availability;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Collection<InstructorAvailability> getAvailability() {
        return availability;
    }

    public void setAvailability(Collection<InstructorAvailability> availability) {
        this.availability = availability;
    }
}
