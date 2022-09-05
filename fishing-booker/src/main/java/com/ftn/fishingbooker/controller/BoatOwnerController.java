package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dao.*;
import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.format.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import java.util.*;
import java.util.stream.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/boat-owner")
public class BoatOwnerController {

    private final BoatOwnerService boatOwnerService;
    private final ReservationService reservationService;
    private final SpecialOfferService specialOfferService;

    public BoatOwnerController(BoatOwnerService boatOwnerService, ReservationService reservationService, SpecialOfferService specialOfferService) {
        this.boatOwnerService = boatOwnerService;
        this.reservationService = reservationService;
        this.specialOfferService = specialOfferService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody OwnerRegisterDto registerDto) throws MessagingException {

        BoatOwner boatOwner = RegistrationMapper.mapToBoatOwner(registerDto);
        User user = boatOwnerService.register(boatOwner, registerDto.motivation);
        return new ResponseEntity<>(UserMapper.mapToDto(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BoatOwnerDto> getOwnerWithAvailability(String email) {
        BoatOwner found = boatOwnerService.getWithAvailability(email);
        return ok(BoatOwnerMapper.toDto(found));
    }

    @GetMapping("/check-if-available")
    ResponseEntity<Boolean> checkAvailability(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date to,
                                              @RequestParam String boatOwnerEmail) {
        return ok(boatOwnerService.checkAvailability(from, to, boatOwnerEmail));
    }

    @GetMapping("/reservations/upcoming")
    ResponseEntity<Collection<BoatReservationInfo>> getUpcomingReservations(@RequestParam String boatOwnerEmail) {
        BoatOwner owner = boatOwnerService.getByEmail(boatOwnerEmail);
        Collection<BoatReservationInfo> reservations = reservationService.getUpcomingReservationsForBoatOwner(owner.getId());
        return ok(reservations);
    }

    @GetMapping("/reservations/past")
    ResponseEntity<Collection<ReservationDto>> getReservationsHistory(@RequestParam String boatOwnerEmail) {
        BoatOwner owner = boatOwnerService.getByEmail(boatOwnerEmail);
        Collection<Reservation> reservations = reservationService.getPastReservationsForBoatOwner(owner.getId());
        Collection<ReservationDto> dtos = reservations.stream()
                .map(ReservationMapper::map)
                .collect(Collectors.toList());
        return ok(dtos);
    }
    @GetMapping("/reservations/current")
    ResponseEntity<Collection<BoatReservationInfo>> getCurrentReservations(@RequestParam String boatOwnerEmail) {
        BoatOwner owner = boatOwnerService.getByEmail(boatOwnerEmail);
        Collection<BoatReservationInfo> reservations  = reservationService.getCurrentReservationsForBoatOwner(owner.getId());
        return ok(reservations);
    }

    @GetMapping("/reservations/captain/{email}")
    ResponseEntity<Collection<ReservationDto>> getAllCaptainReservations(@PathVariable String email) {
        BoatOwner owner = boatOwnerService.getByEmail(email);
        Collection<Reservation> reservations = reservationService.getCaptainReservationsForBoatOwner(owner.getId());
        Collection<ReservationDto> dtos = reservations.stream()
                .map(ReservationMapper::map)
                .collect(Collectors.toList());
        return ok(dtos);
    }

    @GetMapping("/special-offers/captain/{email}")
    ResponseEntity<Collection<SpecialOfferDto>> getAllCaptainSpecialOffers(@PathVariable String email) {
        BoatOwner owner = boatOwnerService.getByEmail(email);
        Collection<SpecialOffer> specialOffers = specialOfferService.getAllCaptainOffersForBoatOwner(owner.getId());
        Collection<SpecialOfferDto> dtos = specialOffers.stream()
                .map(SpecialOfferMapper::toDto)
                .collect(Collectors.toList());
        return ok(dtos);
    }

    @PostMapping("/add-availability")
    public ResponseEntity<Collection<BoatAvailabilityDto>> addAvailabilityPeriod(@RequestBody BoatOwnerAvailabilityRequestDto availability) {
        Collection<BoatOwnerAvailability> availabilities = boatOwnerService.addAvailabilityPeriod(BoatOwnerMapper.mapToBoatOwnerAvailabilityEntity(availability), availability.getEmail());
        Collection<BoatAvailabilityDto> dtos = availabilities.stream()
                .map(BoatMapper::mapToAvailabilityDtoFromOwnerAvailability)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    @PostMapping("/check-if-res-overlaps-avail")
    public ResponseEntity<Boolean> checkIfReservationOverlapsAvailability(@RequestBody BoatOwnerAvailabilityRequestDto availability) {
        return ok(boatOwnerService.checkIfReservationOverlapsAvailability(BoatOwnerMapper.mapToBoatOwnerAvailabilityEntity(availability), availability.getEmail()));

    }

    @PostMapping("/remove-availability")
    public ResponseEntity<Collection<BoatAvailabilityDto>> deleteAvailabilityPeriod(@RequestBody BoatOwnerAvailabilityRequestDto availability) {
        Collection<BoatOwnerAvailability> availabilities = boatOwnerService.updateAvailability(availability.getStartDate(), availability.getEndDate(), availability.getEmail());
        Collection<BoatAvailabilityDto> dtos = availabilities.stream()
                .map(BoatMapper::mapToAvailabilityDtoFromOwnerAvailability)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

}
