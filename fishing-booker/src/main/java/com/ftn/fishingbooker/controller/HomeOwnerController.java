package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dao.*;
import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.*;
import java.util.stream.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/home-owner")
public class HomeOwnerController {

    private final HomeOwnerService homeOwnerService;
    private final ReservationService reservationService;

    public HomeOwnerController(HomeOwnerService homeOwnerService, ReservationService reservationService) {
        this.homeOwnerService = homeOwnerService;
        this.reservationService = reservationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody OwnerRegisterDto registerDto) throws MessagingException {

        HomeOwner homeOwner = RegistrationMapper.mapToHomeOwner(registerDto);
        User user = homeOwnerService.register(homeOwner, registerDto.motivation);
        return new ResponseEntity<>(UserMapper.mapToDto(user), HttpStatus.CREATED);
    }


    @GetMapping("/reservations/upcoming")
    ResponseEntity<Collection<BoatReservationInfo>> getUpcomingReservations(@RequestParam String homeOwnerEmail) {
        HomeOwner owner = homeOwnerService.getByEmail(homeOwnerEmail);
        Collection<BoatReservationInfo> reservations = reservationService.getUpcomingReservationsForHomeOwner(owner.getId());
        return ok(reservations);
    }

    @GetMapping("/reservations/past")
    ResponseEntity<Collection<ReservationDto>> getReservationsHistory(@RequestParam String homeOwnerEmail) {
        HomeOwner owner = homeOwnerService.getByEmail(homeOwnerEmail);
        Collection<Reservation> reservations = reservationService.getPastReservationsForHomeOwner(owner.getId());
        Collection<ReservationDto> dtos = reservations.stream()
                .map(ReservationMapper::map)
                .collect(Collectors.toList());
        return ok(dtos);
    }
    @GetMapping("/reservations/current")
    ResponseEntity<Collection<BoatReservationInfo>> getCurrentReservations(@RequestParam String homeOwnerEmail) {
        HomeOwner owner = homeOwnerService.getByEmail(homeOwnerEmail);
        Collection<BoatReservationInfo> reservations = reservationService.getCurrentReservationsForHomeOwner(owner.getId());
        return ok(reservations);
    }
}
