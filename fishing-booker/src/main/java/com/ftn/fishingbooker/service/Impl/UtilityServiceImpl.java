package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Utility;
import com.ftn.fishingbooker.repository.UtilityRepository;
import com.ftn.fishingbooker.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilityServiceImpl implements UtilityService {

    private final UtilityRepository utilityRepository;

    @Override
    public Utility getByName(String name) {
        return utilityRepository.findByName(name);
    }
    @Override
    public Collection<Utility> getAll() {
        return utilityRepository.findAll();
    }
}
