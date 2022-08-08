package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.InstructorBrowseDto;
import com.ftn.fishingbooker.dto.InstructorDto;
import com.ftn.fishingbooker.model.Instructor;

import java.util.ArrayList;
import java.util.Collection;

public class InstructorMapper {

    public static Collection<InstructorBrowseDto> mapInstructors(Collection<Instructor> instructors) {
        ArrayList<InstructorBrowseDto> instructorsDto = new ArrayList<>();
        if (instructors == null) {
            return instructorsDto;
        }
        for (Instructor instructor : instructors) {
            instructorsDto.add(InstructorMapper.map(instructor));
        }
        return instructorsDto;
    }

    public static InstructorBrowseDto map(Instructor instructor) {
        InstructorBrowseDto instructorDto = new InstructorBrowseDto();
        instructorDto.setId(instructor.getId());
        instructorDto.setRating(instructor.getRating());
        instructorDto.setFirstName(instructor.getFirstName());
        instructorDto.setLastName(instructor.getLastName());
        instructorDto.setAddress(instructor.getAddress());
        instructorDto.setEmail(instructor.getEmail());
        instructorDto.setBiography(instructor.getBiography());

        return instructorDto;
    }
    public static InstructorDto toDto(Instructor instructor){
        InstructorDto dto = new InstructorDto();
        dto.setUser(UserMapper.mapToDto(instructor));
        dto.setAvailability(instructor.getAvailability());
        return dto;
    }
}
