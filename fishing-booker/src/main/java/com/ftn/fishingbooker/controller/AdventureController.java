package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.AdventureDto;
import com.ftn.fishingbooker.mapper.AdventureMapper;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.service.AdventureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adventures")
public class AdventureController {

    private final AdventureService adventureService;

    public AdventureController(AdventureService adventureService){
        this.adventureService = adventureService;
    }

    @GetMapping
    public ResponseEntity<Collection<AdventureDto>> getAllAdventures() {
        Collection<Adventure> found = adventureService.getAll();

        Collection<AdventureDto> dtos = found.stream()
                .map(adventure -> AdventureMapper.mapToDto(adventure))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(dtos);
    }
}
