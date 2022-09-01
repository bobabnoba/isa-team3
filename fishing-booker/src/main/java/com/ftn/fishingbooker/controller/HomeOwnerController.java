package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dao.*;
import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.HomeOwnerService;
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
@RequestMapping("/home-owner")
public class HomeOwnerController {

    private final HomeOwnerService homeOwnerService;

    public HomeOwnerController(HomeOwnerService homeOwnerService) {
        this.homeOwnerService = homeOwnerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody OwnerRegisterDto registerDto) throws MessagingException {

        HomeOwner homeOwner = RegistrationMapper.mapToHomeOwner(registerDto);
        User user = homeOwnerService.register(homeOwner, registerDto.motivation);
        return new ResponseEntity<>(UserMapper.mapToDto(user), HttpStatus.CREATED);
    }


//    @GetMapping("/reservations/upcoming")
//    ResponseEntity<Collection<HomeReservationInfo>> getUpcomingReservations(@RequestParam String boatOwnerEmail) {
//        BoatOwner owner = boatOwnerService.getByEmail(boatOwnerEmail);
//        Collection<BoatReservationInfo> reservations = reservationService.getUpcomingReservationsForBoatOwner(owner.getId());
//        return ok(reservations);
//    }
//
//    @GetMapping("/reservations/past")
//    ResponseEntity<Collection<ReservationDto>> getReservationsHistory(@RequestParam String boatOwnerEmail) {
//        BoatOwner owner = boatOwnerService.getByEmail(boatOwnerEmail);
//        Collection<Reservation> reservations = reservationService.getPastReservationsForBoatOwner(owner.getId());
//        Collection<ReservationDto> dtos = reservations.stream()
//                .map(ReservationMapper::map)
//                .collect(Collectors.toList());
//        return ok(dtos);
//    }
//    @GetMapping("/reservations/current")
//    ResponseEntity<Collection<BoatReservationInfo>> getCurrentReservations(@RequestParam String boatOwnerEmail) {
//        BoatOwner owner = boatOwnerService.getByEmail(boatOwnerEmail);
//        Collection<BoatReservationInfo> reservations  = reservationService.getCurrentReservationsForBoatOwner(owner.getId());
//        return ok(reservations);
//    }
}
