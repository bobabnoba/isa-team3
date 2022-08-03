package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.repository.AdventureRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/adventures")
public class AdventureController {

    private final AdventureRepository adventureRepository;

    public AdventureController(AdventureRepository adventureRepository){
        this.adventureRepository = adventureRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Adventure>> getAllAdventures() {
        return ResponseEntity.ok(adventureRepository.findAll());
    }
}
