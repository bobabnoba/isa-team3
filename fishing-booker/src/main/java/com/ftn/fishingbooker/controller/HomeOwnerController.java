package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.OwnerRegisterDto;
import com.ftn.fishingbooker.mapper.RegistrationMapper;
import com.ftn.fishingbooker.mapper.UserMapper;
import com.ftn.fishingbooker.model.BoatOwner;
import com.ftn.fishingbooker.model.HomeOwner;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.service.HomeOwnerService;
import com.ftn.fishingbooker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/home-owner")
public class HomeOwnerController {

    private final HomeOwnerService homeOwnerService;

    public HomeOwnerController(HomeOwnerService homeOwnerService) {
        this.homeOwnerService = homeOwnerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody OwnerRegisterDto registerDto) throws MessagingException {

        HomeOwner homeOwner = RegistrationMapper.mapToHomeOwner(registerDto);
        User user = homeOwnerService.register(homeOwner, registerDto.motivation);
        return new ResponseEntity<>(UserMapper.mapToDto(user), HttpStatus.CREATED);
    }
}
