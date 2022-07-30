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

    private final RegistrationRepository registrationRepository;
    private final AdminService adminService;

    public AdminController(RegistrationRepository registrationRepository, AdminService adminService){
        this.registrationRepository = registrationRepository;
        this.adminService = adminService;
    }

    @GetMapping(path = "registerRequests")
    public ResponseEntity<Object> getAllRegistrationRequests(){
        return ResponseEntity.ok().body(registrationRepository.findUnprocessedRequests());
    }


    @PostMapping("/approveRequest")
    public ResponseEntity<Object> registrationRequestApproval(@RequestBody RegistrationResponseDto registrationResponseDto) throws MessagingException {
        adminService.respondToRegistrationRequest(registrationResponseDto);
        return ResponseEntity.ok().build();
    }
}
