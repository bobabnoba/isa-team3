package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.AddressRepository;
import com.ftn.fishingbooker.repository.AdventureRepository;
import com.ftn.fishingbooker.repository.FishingEquipmentRepository;
import com.ftn.fishingbooker.repository.InstructorRepository;
import com.ftn.fishingbooker.service.AdventureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdventureServiceImpl implements AdventureService {

    private final AdventureRepository adventureRepository;
    private final InstructorRepository instructorRepository;
    private final AddressRepository addressRepository;
    private final FishingEquipmentRepository fishingEquipmentRepository;

    public AdventureServiceImpl(AdventureRepository adventureRepository, InstructorRepository instructorRepository, AddressRepository addressRepository, FishingEquipmentRepository fishingEquipmentRepository) {
        this.adventureRepository = adventureRepository;
        this.instructorRepository = instructorRepository;
        this.addressRepository = addressRepository;
        this.fishingEquipmentRepository = fishingEquipmentRepository;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Collection<Adventure> getAll() {
        return adventureRepository.findAll();
    }

    @Override
    public void addImage(Long adventureId, String fileName) {
        Adventure found = adventureRepository.findById(adventureId)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));
        found.getImages().add(new Image(fileName));
        adventureRepository.save(found);
    }

    @Override
    @Transactional
    public Adventure addAdventure(Adventure adventure, String instructorEmail) {
        Address savedAddress = addressRepository.save(adventure.getAddress());
        Set<FishingEquipment> fishingEquipment = adventure.getFishingEquipment().stream()
                .map(fe -> fishingEquipmentRepository.findById(fe.getId()).get())
                .collect(Collectors.toSet());
        Instructor instructor = instructorRepository.findByEmail(instructorEmail);

        adventure.setInstructor(instructor);
        adventure.setAddress(savedAddress);
        adventure.setFishingEquipment(fishingEquipment);
        return adventureRepository.save(adventure);
    }
}

