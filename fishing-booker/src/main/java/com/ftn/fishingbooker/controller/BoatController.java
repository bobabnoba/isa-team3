package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.FilterDto;
import com.ftn.fishingbooker.dto.RentalDto;
import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.RentalMapper;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.model.Boat;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.service.BoatService;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/boats")
@RequiredArgsConstructor
public class BoatController {

    private final BoatService boatService;
    private final ClientService clientService;
    private final ReservationService reservationService;

    @GetMapping()
    public Collection<RentalDto> GetAll() {
        Collection<Boat> boats = boatService.getAll();

        return RentalMapper.mapBoatToRental(boats);
    }

    @PostMapping("/search")
    public ResponseEntity<Collection<RentalDto>> FilterAll(@RequestBody FilterDto filter) {
        if (clientService.hasOverlappingReservation(filter.getEmail(), filter.getStartDate(), filter.getEndDate())) {

            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Collection<Boat> boats = boatService.filterAll(filter);
        Collection<RentalDto> rentals = RentalMapper.mapBoatToRental(boats);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @PostMapping("/rent/{boatId}/{userEmail}")
    public ResponseEntity<ReservationDto> makeReservation(@PathVariable String userEmail, @PathVariable Long boatId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);

        reservationDto.setType(ReservationType.BOAT);
        Reservation reservation = reservationService.makeReservation(client, reservationDto);
        boatService.makeReservation(boatId, reservation);

        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);
    }

}
