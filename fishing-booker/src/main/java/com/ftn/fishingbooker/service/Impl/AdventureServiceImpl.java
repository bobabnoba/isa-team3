package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.AdventureAdditionalInfo;
import com.ftn.fishingbooker.dto.AdventureInfo;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdventureServiceImpl implements AdventureService {

    private final AdventureRepository adventureRepository;
    private final InstructorRepository instructorRepository;
    private final AddressRepository addressRepository;
    private final FishingEquipmentRepository fishingEquipmentRepository;
    private final UtilityRepository utilityRepository;
    private final RuleRepository ruleRepository;

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

        Set<Rule> rules = adventure.getCodeOfConduct().stream()
                .map(r -> ruleRepository.findById(r.getId()).get())
                .collect(Collectors.toSet());

        Set<Utility> utilities = adventure.getUtilities().stream()
                .map(u -> utilityRepository.findById(u.getId()).get())
                .collect(Collectors.toSet());

        adventure.setInstructor(instructor);
        adventure.setAddress(savedAddress);
        adventure.setFishingEquipment(fishingEquipment);
        adventure.setUtilities(utilities);
        adventure.setCodeOfConduct(rules);

        return adventureRepository.save(adventure);
    }

    @Override
    public Adventure getById(Long id) {
        return adventureRepository.findById(id).orElseThrow(() -> new ResourceConflictException("Adventure not found"));
    }

    @Override
    public Adventure updateAdventureInfo(Long id,  AdventureInfo updated) {
        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        found.setTitle(updated.getTitle());
        found.setDescription(updated.getDescription());
        found.setPricePerDay(updated.getPrice());
        found.setMaxNumberOfParticipants(updated.getMaxParticipants());
        found.setCancelingPercentage(updated.getCancelingPercentage());

        return adventureRepository.save(found);
    }

    @Override
    public Adventure updateAdventureAdditionalInfo(Long id, AdventureAdditionalInfo updated) {

        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        Set<FishingEquipment> fishingEquipment = updated.getFishingEquipment().stream()
                .map(fe -> fishingEquipmentRepository.findById(fe.getId()).get())
                .collect(Collectors.toSet());

        Set<Utility> utilities = updated.getUtilities().stream()
                .map(u -> utilityRepository.findById(u.getId()).get())
                .collect(Collectors.toSet());

        found.setFishingEquipment(fishingEquipment);
        found.setUtilities(utilities);
        return adventureRepository.save(found);
    }

    @Override
    public Adventure updateAdventureRules(Long id, Collection<Rule> updated) {
        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        Set<Rule> rules = updated.stream()
                .map(r -> ruleRepository.findById(r.getId()).get())
                .collect(Collectors.toSet());
        found.setCodeOfConduct(rules);

        return adventureRepository.save(found);
    }

    @Override
    public Adventure updateAdventureAddress(Long id, Address updated) {
        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        addressRepository.save(updated);
        found.setAddress(updated);
        return adventureRepository.save(found);
    }

}

