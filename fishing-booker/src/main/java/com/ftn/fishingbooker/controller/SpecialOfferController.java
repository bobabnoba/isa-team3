package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.model.SpecialOffer;
import com.ftn.fishingbooker.service.SpecialOfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/special-offers")
public class SpecialOfferController {

    private final SpecialOfferService specialOfferService;

    public SpecialOfferController(SpecialOfferService specialOfferService) {
        this.specialOfferService = specialOfferService;
    }

    @PostMapping("/{serviceId}")
    public ResponseEntity<SpecialOffer> createSpecialOffer(@RequestBody  SpecialOffer specialOffer,
                                                           @PathVariable  Long serviceId) {
        return ResponseEntity.ok(specialOfferService.createSpecialOffer(specialOffer, serviceId));
    }
}
