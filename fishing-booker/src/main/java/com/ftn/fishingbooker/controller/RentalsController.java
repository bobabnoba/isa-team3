package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.RentalDto;
import com.ftn.fishingbooker.mapper.RentalMapper;
import com.ftn.fishingbooker.service.BoatService;
import com.ftn.fishingbooker.service.HomeService;
import com.ftn.fishingbooker.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalsController {
    private final BoatService boatService;
    private final HomeService vacationHomeService;
    private final InstructorService instructorService;


    @GetMapping
    public Collection<RentalDto> getAllRentals() {
        Collection<RentalDto> allRentals = new ArrayList<>();
        Collection<RentalDto> boatRentals = RentalMapper.mapBoatToRental(boatService.getAll());
        Collection<RentalDto> vacationHomeRentals = RentalMapper.mapVacationHomeToRental(vacationHomeService.getAll());
        Collection<RentalDto> instructorRentals = RentalMapper.mapInstructorToRental(instructorService.getAll());
        allRentals.addAll(boatRentals);
        allRentals.addAll(vacationHomeRentals);
        allRentals.addAll(instructorRentals);

        return allRentals;
    }
}
