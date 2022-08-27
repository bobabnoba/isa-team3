package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.mapper.AdminMapper;
import com.ftn.fishingbooker.mapper.RegistrationMapper;
import com.ftn.fishingbooker.model.Admin;
import com.ftn.fishingbooker.model.Registration;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final RegistrationRepository registrationRepository;
    private final AdminService adminService;

    public AdminController(RegistrationRepository registrationRepository, AdminService adminService){
        this.registrationRepository = registrationRepository;
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<AdminDto> addNewAdmin(@RequestBody AdminDto newAdmin){
        Admin added = adminService.addNewAdmin(AdminMapper.toEntity(newAdmin));
        return ResponseEntity.ok(AdminMapper.toDto(added));
    }

    @GetMapping("/first-login/{email}")
    public ResponseEntity<Boolean> firstLogin(@PathVariable String email){
        return ResponseEntity.ok(adminService.isFirstLogin(email));
    }

    @GetMapping("registration-requests")
    public ResponseEntity<Object> getAllRegistrationRequests(){
        Collection<Registration> requests = registrationRepository.findUnprocessedRequests();
        Collection<RegistrationResponseDto> requestDtos = requests.stream()
                .map(RegistrationMapper::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(requestDtos);
    }


    @PostMapping("/handle-request")
    public ResponseEntity<Object> handleRegistrationRequest(@RequestBody RegistrationResponseDto registrationResponseDto) throws MessagingException {
        adminService.respondToRegistrationRequest(RegistrationMapper.mapToRegistration(registrationResponseDto));
        return ResponseEntity.ok().build();
    }
}
