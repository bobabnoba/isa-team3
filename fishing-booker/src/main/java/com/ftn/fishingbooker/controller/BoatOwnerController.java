package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.BoatOwnerService;
import org.springframework.format.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/boat-owner")
public class BoatOwnerController {

    private final BoatOwnerService boatOwnerService;

    public BoatOwnerController(BoatOwnerService boatOwnerService) {
        this.boatOwnerService = boatOwnerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody OwnerRegisterDto registerDto) throws MessagingException {

        BoatOwner boatOwner = RegistrationMapper.mapToBoatOwner(registerDto);
        User user = boatOwnerService.register(boatOwner, registerDto.motivation);
        return new ResponseEntity<>(UserMapper.mapToDto(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BoatOwnerDto> getInstructorWithAvailability(String email) {
        BoatOwner found = boatOwnerService.getWithAvailability(email);
        return ok(BoatOwnerMapper.toDto(found));
    }

    @GetMapping("/check-if-available")
    ResponseEntity<Boolean> checkAvailability(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date to,
                                              @RequestParam String boatOwnerEmail) {
        return ok(boatOwnerService.checkAvailability(from, to, boatOwnerEmail));
    }

}
