package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.UtilityDto;
import com.ftn.fishingbooker.mapper.UtilityMapper;
import com.ftn.fishingbooker.model.Utility;
import com.ftn.fishingbooker.service.UtilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/utilities")
public class UtilityController {

    private final UtilityService utilityService;

    public UtilityController(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    @GetMapping
    public ResponseEntity<Collection<UtilityDto>> getAdventureUtilities() {
        Collection<Utility> found = utilityService.getAll();
       return ResponseEntity.ok(UtilityMapper.map(found));
    }

}
