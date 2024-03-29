package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.AdminDto;
import com.ftn.fishingbooker.dto.RegistrationResponseDto;
import com.ftn.fishingbooker.mapper.AdminMapper;
import com.ftn.fishingbooker.mapper.RegistrationMapper;
import com.ftn.fishingbooker.model.Admin;
import com.ftn.fishingbooker.model.Registration;
import com.ftn.fishingbooker.repository.RegistrationRepository;
import com.ftn.fishingbooker.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final RegistrationRepository registrationRepository;
    private final AdminService adminService;

    public AdminController(RegistrationRepository registrationRepository, AdminService adminService) {
        this.registrationRepository = registrationRepository;
        this.adminService = adminService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminDto> addNewAdmin(@RequestBody AdminDto newAdmin) {
        Admin added = adminService.addNewAdmin(AdminMapper.toEntity(newAdmin));
        return ResponseEntity.ok(AdminMapper.toDto(added));
    }

    @GetMapping("/first-login/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> firstLogin(@PathVariable String email) {
        return ResponseEntity.ok(adminService.isFirstLogin(email));
    }

    @GetMapping("registration-requests")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllRegistrationRequests() {
        Collection<Registration> requests = registrationRepository.findUnprocessedRequests();
        Collection<RegistrationResponseDto> requestDtos = requests.stream()
                .map(RegistrationMapper::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(requestDtos);
    }

    @PostMapping("/handle-request")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> handleRegistrationRequest(@RequestBody RegistrationResponseDto registrationResponseDto) throws MessagingException {
        adminService.respondToRegistrationRequest(RegistrationMapper.mapToRegistration(registrationResponseDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/head")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminDto> getHeadAdmin() {
        Admin admin = adminService.getHeadAdmin();
        return ResponseEntity.ok(AdminMapper.toDto(admin));
    }
}
