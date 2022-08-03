package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.OwnerRegisterDto;
import com.ftn.fishingbooker.mapper.RegistrationMapper;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.BoatOwner;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.service.BoatOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

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
}
