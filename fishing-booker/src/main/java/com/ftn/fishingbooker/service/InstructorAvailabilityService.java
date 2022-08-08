package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.InstructorAvailability;

public interface InstructorAvailabilityService {

    InstructorAvailability save(InstructorAvailability instructorAvailability);

    void delete(Long id);

}
