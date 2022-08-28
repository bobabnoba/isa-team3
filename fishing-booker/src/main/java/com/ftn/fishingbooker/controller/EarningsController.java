package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.EarningsDto;
import com.ftn.fishingbooker.mapper.EarningsMapper;
import com.ftn.fishingbooker.model.Earnings;
import com.ftn.fishingbooker.service.EarningsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/earnings")
public class EarningsController {

    private final EarningsService  earningsService;

    public EarningsController(EarningsService earningsService) {
        this.earningsService = earningsService;
    }

    @GetMapping
    public ResponseEntity<Collection<EarningsDto>> getAllForAdvertiserInDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to){
        Collection<Earnings> found = earningsService.getAllInDateRange(from, to);
        Collection<EarningsDto> dtos = found.stream()
                .map(EarningsMapper::toDto)
                .collect(Collectors.toList());
        return ok(dtos);
    }


    @GetMapping("/advertiser/{email}")
    public ResponseEntity<Collection<EarningsDto>> getAllForAdvertiserInDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            @PathVariable String email) {
        Collection<Earnings> found = earningsService.getAllForAdvertiserInDateRange(from, to, email);
        Collection<EarningsDto> dtos = found.stream()
                .map(EarningsMapper::toDto)
                .collect(Collectors.toList());
        return ok(dtos);
    }

}
