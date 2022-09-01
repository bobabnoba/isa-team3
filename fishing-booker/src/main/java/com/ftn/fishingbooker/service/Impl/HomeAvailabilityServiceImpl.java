package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class HomeAvailabilityServiceImpl implements HomeAvailabilityService {

    private final HomeAvailabilityRepository homeAvailabilityRepository;

    public HomeAvailabilityServiceImpl(HomeAvailabilityRepository homeAvailabilityRepository){
        this.homeAvailabilityRepository = homeAvailabilityRepository;
    }

    @Override
    public VacationHomeAvailability save(VacationHomeAvailability homeAvailability) {
        return homeAvailabilityRepository.save(homeAvailability);
    }

    @Override
    public void delete(Long id) {
        VacationHomeAvailability found = homeAvailabilityRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("VacationHomeAvailability not found"));
        homeAvailabilityRepository.delete(found);
    }

    @Override
    public void delete(Set<VacationHomeAvailability> availabilitySet) {
        homeAvailabilityRepository.deleteAll(availabilitySet);
    }
}
