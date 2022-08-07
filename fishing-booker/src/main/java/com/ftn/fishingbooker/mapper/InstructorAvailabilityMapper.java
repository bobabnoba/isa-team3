package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.InstructorAvailabilityRequestDto;
import com.ftn.fishingbooker.dto.InstructorAvailabilityResponseDto;
import com.ftn.fishingbooker.model.InstructorAvailability;

public class InstructorAvailabilityMapper {

    public static InstructorAvailability mapToEntity(InstructorAvailabilityRequestDto dto) {
        InstructorAvailability entity = new InstructorAvailability();
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        return entity;
    }

    public static InstructorAvailabilityResponseDto mapToResponse(InstructorAvailability entity) {
        InstructorAvailabilityResponseDto dto = new InstructorAvailabilityResponseDto();
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setId(entity.getId());
        return dto;
    }
}
