package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import org.springframework.stereotype.*;
import com.ftn.fishingbooker.service.BoatOwnerAvailabilityService;

import java.util.*;

@Service
public class BoatOwnerAvailabilityServiceImpl implements BoatOwnerAvailabilityService {

    private final BoatOwnerAvailabilityRepository boatOwnerAvailabilityRepository;
    public BoatOwnerAvailabilityServiceImpl(BoatOwnerAvailabilityRepository boatOwnerAvailabilityRepository) {
        this.boatOwnerAvailabilityRepository = boatOwnerAvailabilityRepository;
    }

    @Override
    public BoatOwnerAvailability save(BoatOwnerAvailability boatOwnerAvailability) {
        return boatOwnerAvailabilityRepository.save(boatOwnerAvailability);
    }

    @Override
    public void delete(Long id) {
        BoatOwnerAvailability found = boatOwnerAvailabilityRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("BoatOwnerAvailability not found"));
        boatOwnerAvailabilityRepository.delete(found);
    }

    @Override
    public void delete(Set<BoatOwnerAvailability> availabilityStream) {
        boatOwnerAvailabilityRepository.deleteAll(availabilityStream);
    }
}
