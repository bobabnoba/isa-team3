package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.ClientDto;
import com.ftn.fishingbooker.mapper.ClientMapper;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @GetMapping("/check-if-available")
    @PreAuthorize("hasAnyRole('HOME_OWNER', 'BOAT_OWNER', 'INSTRUCTOR')")
    public ResponseEntity<Boolean> checkIfAvailable(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date to,
                                                    @RequestParam String email) {
        return ResponseEntity.ok(!clientService.hasOverlappingReservation(email, from, to));
    }

    @GetMapping("{email}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public ResponseEntity<ClientDto> getClientInfo(@PathVariable String email) {
        Client clientInfo = clientService.getClientByEmail(email);
        return ResponseEntity.ok(ClientMapper.map(clientInfo));
    }

    @GetMapping("/{clientEmail}/subscribed/{entityType}/{entityId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public boolean isClientSubscribed(@PathVariable String clientEmail, @PathVariable String entityType, @PathVariable Long entityId) {
        return clientService.isClientSubscribed(clientEmail, entityType, entityId);
    }

}
