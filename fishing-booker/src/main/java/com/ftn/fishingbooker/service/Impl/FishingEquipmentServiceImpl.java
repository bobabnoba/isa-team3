package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.FishingEquipment;
import com.ftn.fishingbooker.repository.FishingEquipmentRepository;
import com.ftn.fishingbooker.service.FishingEquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishingEquipmentServiceImpl implements FishingEquipmentService {

    private final FishingEquipmentRepository fishingEquipmentRepository;

    public FishingEquipmentServiceImpl(FishingEquipmentRepository fishingEquipmentRepository) {
        this.fishingEquipmentRepository = fishingEquipmentRepository;
    }

    @Override
    public List<FishingEquipment> getAll() {
        return fishingEquipmentRepository.findAll();
    }
}
