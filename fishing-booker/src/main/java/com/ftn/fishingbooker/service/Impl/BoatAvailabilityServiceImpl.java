package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.stereotype.*;

@Service
public class BoatAvailabilityServiceImpl implements BoatAvailabilityService {

    private final BoatAvailabilityRepository boatAvailabilityRepository;

    public BoatAvailabilityServiceImpl(BoatAvailabilityRepository boatAvailabilityRepository) {
        this.boatAvailabilityRepository = boatAvailabilityRepository;
    }

    @Override
    public BoatAvailability save(BoatAvailability boatAvailability) {
        return boatAvailabilityRepository.save(boatAvailability);
    }

    @Override
    public void delete(Long id) {
        BoatAvailability found = boatAvailabilityRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("BoatAvailability not found"));
        boatAvailabilityRepository.delete(found);
    }
}
