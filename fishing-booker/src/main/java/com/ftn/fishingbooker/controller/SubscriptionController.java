package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.RentalDto;
import com.ftn.fishingbooker.mapper.RentalMapper;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    final private SubscriptionService subscriptionService;
    final private ClientService clientService;

    @PostMapping("/subscribe/{clientEmail}/{entityType}/{entityId}")
    @PreAuthorize("hasRole('CLIENT')")
    public void subscribe(@PathVariable String clientEmail, @PathVariable String entityType, @PathVariable Long entityId) {
        subscriptionService.subscribe(clientEmail, entityType, entityId);
    }

    @PostMapping("/unsubscribe/{clientEmail}/{entityType}/{entityId}")
    @PreAuthorize("hasRole('CLIENT')")
    public void unsubscribe(@PathVariable String clientEmail, @PathVariable String entityType, @PathVariable Long entityId) {
        subscriptionService.unsubscribe(clientEmail, entityType, entityId);
    }

    @GetMapping("/boats/{clientEmail}")
    @PreAuthorize("hasRole('CLIENT')")
    public Collection<RentalDto> getClientBoatSubscriptions(@PathVariable String clientEmail) {
        return RentalMapper.mapBoatToRental(clientService.getBoatSubscription(clientEmail));
    }

    @GetMapping("/vacation/homes/{clientEmail}")
    @PreAuthorize("hasRole('CLIENT')")
    public Collection<RentalDto> getClientVacationHomeSubscriptions(@PathVariable String clientEmail) {
        return RentalMapper.mapVacationHomeToRental(clientService.getVacationHomeSubscription(clientEmail));
    }

    @GetMapping("/instructors/{clientEmail}")
    @PreAuthorize("hasRole('CLIENT')")
    public Collection<RentalDto> getClientInstructorsSubscriptions(@PathVariable String clientEmail) {
        return RentalMapper.mapInstructorToRental(clientService.getInstructorSubscription(clientEmail));
    }
}
