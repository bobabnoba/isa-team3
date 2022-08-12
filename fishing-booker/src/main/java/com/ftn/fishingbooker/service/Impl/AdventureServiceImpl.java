package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.AdventureService;
import jdk.jshell.execution.Util;
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
    private final UtilityRepository utilityRepository;

    public AdventureServiceImpl(AdventureRepository adventureRepository, InstructorRepository instructorRepository, AddressRepository addressRepository, FishingEquipmentRepository fishingEquipmentRepository, UtilityRepository utilityRepository) {
        this.adventureRepository = adventureRepository;
        this.instructorRepository = instructorRepository;
        this.addressRepository = addressRepository;
        this.fishingEquipmentRepository = fishingEquipmentRepository;
        this.utilityRepository = utilityRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
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
        Instructor instructor = instructorRepository.findByEmail(instructorEmail);

        Set<FishingEquipment> fishingEquipment = adventure.getFishingEquipment().stream()
                .map(fe -> fishingEquipmentRepository.findById(fe.getId()).get())
                .collect(Collectors.toSet());

        Set<Utility> utilities = adventure.getUtilities().stream()
                .map(u -> utilityRepository.findById(u.getId()).get())
                .collect(Collectors.toSet());

        adventure.setInstructor(instructor);
        adventure.setAddress(savedAddress);
        adventure.setFishingEquipment(fishingEquipment);
        adventure.setUtilities(utilities);

        return adventureRepository.save(adventure);
    }

    @Override
    public Adventure getById(Long id) {
        return adventureRepository.findById(id).orElseThrow(() -> new ResourceConflictException("Adventure not found"));
    }

    @Override
    public Adventure updateAdventureInfo(Long id, Adventure adventure) {
        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        found.setTitle(adventure.getTitle());
        found.setDescription(adventure.getDescription());
        found.setPricePerDay(adventure.getPricePerDay());
        found.setMaxNumberOfParticipants(adventure.getMaxNumberOfParticipants());
        found.setCancelingPercentage(adventure.getCancelingPercentage());
        return adventureRepository.save(found);
    }
}

