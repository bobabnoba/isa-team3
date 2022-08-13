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

@Service
@RequiredArgsConstructor
 public class AdventureServiceImpl implements AdventureService {

    private final AdventureRepository adventureRepository;
    private final InstructorRepository instructorRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public Collection<Adventure> getAll() {
        return adventureRepository.findAll();
    }

    @Override
    public Collection<Adventure> getAllByInstructorEmail(String email) {
        Instructor instructor = instructorRepository.findByEmail(email);
        if(instructor == null) {
            throw new ResourceConflictException("Instructor with email " + email + " does not exist");
        }
        return adventureRepository.findAllByInstructorId(instructor.getId());
    }

    @Override
    @Transactional
    public void addImage(Long adventureId, String fileName) {
        Adventure found = adventureRepository.findById(adventureId)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));
        found.getImages().add(new Image(fileName));
        adventureRepository.save(found);
    }

    @Override
    @Transactional
    public Adventure addAdventure(Adventure adventure, String instructorEmail) {
        Instructor instructor = instructorRepository.findByEmail(instructorEmail);
        adventure.setInstructor(instructor);
        return adventureRepository.save(adventure);
    }

    @Override
    @Transactional(readOnly = true)
    public Adventure getById(Long id) {
        return adventureRepository.findById(id).orElseThrow(() -> new ResourceConflictException("Adventure not found"));
    }

    @Override
    @Transactional
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
    @Transactional
    public Adventure updateAdventureAdditionalInfo(Long id, AdventureAdditionalInfo updated) {

        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        found.setFishingEquipment(new HashSet<>(updated.getFishingEquipment()));
        found.setUtilities(new HashSet<>(updated.getUtilities()));
        return adventureRepository.save(found);
    }

    @Override
    @Transactional
    public Adventure updateAdventureRules(Long id, Collection<Rule> updated) {
        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        found.setCodeOfConduct(new HashSet<>(updated));
        return adventureRepository.save(found);
    }

    @Override
    @Transactional
    public Adventure updateAdventureAddress(Long id, Address updated) {
        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));

        found.setAddress(updated);
        return adventureRepository.save(found);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Adventure found = adventureRepository.findById(id)
                .orElseThrow(() -> new ResourceConflictException("Adventure not found"));
        found.setIsDeleted(true);
        adventureRepository.save(found);
    }

}

