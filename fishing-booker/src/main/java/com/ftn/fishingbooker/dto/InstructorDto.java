package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.InstructorAvailability;
import lombok.Data;

import java.util.Collection;

@Data
public class InstructorDto {
    public  UserDto user;
    public Collection<InstructorAvailability> availability;


}
