package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.*;

public class AdminController {

    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    AdminService adminService;

    @GetMapping
    public ResponseEntity<Object> getAllRegistrationRequests(){
        return new ResponseEntity<>(registrationRepository.findAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/admin/approveRequest")
    public ResponseEntity<Object> registrationRequestApproval(RegistrationResponseDto registrationResponseDto) throws MessagingException {
        adminService.respondToRegistrationRequest(registrationResponseDto);
        return null;
    }
}
