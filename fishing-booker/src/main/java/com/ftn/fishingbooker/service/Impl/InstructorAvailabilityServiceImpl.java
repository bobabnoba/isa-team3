package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.InstructorAvailability;
import com.ftn.fishingbooker.repository.InstructorAvailabilityRepository;
import com.ftn.fishingbooker.service.InstructorAvailabilityService;
import org.springframework.stereotype.Service;

@Service
public class InstructorAvailabilityServiceImpl implements InstructorAvailabilityService {

    private final InstructorAvailabilityRepository instructorAvailabilityRepository;

    public InstructorAvailabilityServiceImpl(InstructorAvailabilityRepository instructorAvailabilityRepository) {
        this.instructorAvailabilityRepository = instructorAvailabilityRepository;
    }

    @Override
    public InstructorAvailability save(InstructorAvailability instructorAvailability) {
        return instructorAvailabilityRepository.save(instructorAvailability);
    }

    @Override
    public void delete(Long id) {
        InstructorAvailability found = instructorAvailabilityRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("InstructorAvailability not found"));
        instructorAvailabilityRepository.delete(found);
    }
}
