package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.adventure.Adventure;
import com.ftn.fishingbooker.repository.AdventureRepository;
import com.ftn.fishingbooker.service.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class AdventureServiceImpl implements AdventureService {
    private final AdventureRepository adventureRepository;

    @Override
    public ArrayList<Adventure> getAll() {
        return adventureRepository.getAll();
    }
}
