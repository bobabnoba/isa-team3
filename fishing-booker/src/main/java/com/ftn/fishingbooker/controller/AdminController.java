package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    AdminService adminService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "registerRequests")
    public ResponseEntity<Object> getAllRegistrationRequests(){
        return new ResponseEntity<>(registrationRepository.findUnprocessedRequests(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/approveRequest")
    public ResponseEntity<Object> registrationRequestApproval(@RequestBody RegistrationResponseDto registrationResponseDto) throws MessagingException {
        adminService.respondToRegistrationRequest(registrationResponseDto);
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/deleteAccount")
    public ResponseEntity<Object> deleteAccount(@RequestBody DeleteAccountResponseDto deleteAccountResponseDto) throws MessagingException {
        adminService.deleteAccount(deleteAccountResponseDto);
        return null;
    }
}
