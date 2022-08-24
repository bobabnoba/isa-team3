package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.InstructorAvailability;
import lombok.Data;

import java.util.Collection;

@Data
public class InstructorInfoDto {
    public InstructorBrowseDto instructor;
    public Collection<InstructorAvailability> availability;


}
