package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.InstructorDto;
import com.ftn.fishingbooker.model.Instructor;

public class InstructorMapper {

    public static InstructorDto toDto(Instructor instructor){
        InstructorDto dto = new InstructorDto();
        dto.setUser(UserMapper.mapToDto(instructor));
        dto.setAvailability(instructor.getAvailability());
        return dto;
    }
}
