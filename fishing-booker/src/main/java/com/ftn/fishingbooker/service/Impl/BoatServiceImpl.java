package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Boat;
import com.ftn.fishingbooker.repository.BoatRepository;
import com.ftn.fishingbooker.service.BoatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class BoatServiceImpl implements BoatService {
    private final BoatRepository boatRepository;

    @Override
    public Collection<Boat> getAll() {
        return boatRepository.getAll();
    }
}
