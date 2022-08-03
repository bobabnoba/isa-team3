package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.RentalDto;
import com.ftn.fishingbooker.mapper.RentalMapper;
import com.ftn.fishingbooker.service.AdventureService;
import com.ftn.fishingbooker.service.BoatService;
import com.ftn.fishingbooker.service.VacationHomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalsController {

    private final BoatService boatService;
    private final VacationHomeService vacationHomeService;
    private final AdventureService adventureService;


    @GetMapping
    public ArrayList<RentalDto> getAllRentals() {
        ArrayList<RentalDto> allRentals = new ArrayList<>();
        ArrayList<RentalDto> boatRentals = RentalMapper.mapBoatToRental(boatService.getAll());
        ArrayList<RentalDto> vacationHomeRentals = RentalMapper.mapVacationHomeToRental(vacationHomeService.getAll());
        ArrayList<RentalDto> adventureRentals = RentalMapper.mapInstructorToRental(adventureService.getAll());
        allRentals.addAll(boatRentals);
        allRentals.addAll(vacationHomeRentals);
        allRentals.addAll(adventureRentals);

        return allRentals;
    }
}
