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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/boat-owner")
public class BoatOwnerController {

    private final BoatOwnerService boatOwnerService;
    private final ReservationService reservationService;

    public BoatOwnerController(BoatOwnerService boatOwnerService, ReservationService reservationService) {
        this.boatOwnerService = boatOwnerService;
        this.reservationService = reservationService;
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

//    @GetMapping("ongoing-reservation-client/{email}")
//    public ResponseEntity<UserDto> getOngoingReservationClient(@PathVariable String email) {
//        Reservation reservation = instructorService.getOngoingReservationForInstructor(email);
//        if (reservation != null) {
//            return ResponseEntity.ok(UserMapper.mapToDto(userService.getUserById(reservation.getClient().getId())));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
