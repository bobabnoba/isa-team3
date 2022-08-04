package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.repository.AdventureRepository;
import com.ftn.fishingbooker.service.AdventureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("/adventures")
public class AdventureController {

    private final AdventureService adventureService;

    public AdventureController(AdventureService adventureService){
        this.adventureService = adventureService;
    }

    @GetMapping
    public ResponseEntity<Collection<Adventure>> getAllAdventures() {
        return ResponseEntity.ok(adventureService.getAll());
    }
}
