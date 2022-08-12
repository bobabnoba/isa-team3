package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Utility;
import com.ftn.fishingbooker.repository.UtilityRepository;
import com.ftn.fishingbooker.service.UtilityService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UtilityServiceImpl implements UtilityService {

    private final UtilityRepository utilityRepository;

    public UtilityServiceImpl(UtilityRepository utilityRepository) {
        this.utilityRepository = utilityRepository;
    }

    @Override
    public Collection<Utility> getAll() {
        return utilityRepository.findAll();
    }
}
