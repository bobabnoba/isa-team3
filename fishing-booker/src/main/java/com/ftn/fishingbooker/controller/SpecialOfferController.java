package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.NewSpecialOfferDto;
import com.ftn.fishingbooker.mapper.SpecialOfferMapper;
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
    public ResponseEntity<SpecialOffer> createSpecialOffer(@RequestBody NewSpecialOfferDto specialOffer,
                                                           @PathVariable  Long serviceId) {
        SpecialOffer offer = SpecialOfferMapper.toNewEntity(specialOffer);
        return ResponseEntity.ok(specialOfferService.createSpecialOffer(offer, serviceId));
    }
}
