package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ClientService clientService;
    private final AdventureService adventureService;
    private final HomeService homeService;
    private final BoatService boatService;
    private final InstructorService instructorService;
    private final BoatOwnerService boatOwnerService;


    @GetMapping("{id}")
    public ResponseEntity<ReservationWithClientDto> getReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(ReservationMapper.toDtoWClient(reservation));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Reservation> GetReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/upcoming/{userEmail}")
    public Collection<ReservationDto> GetUpcomingReservations(@PathVariable String userEmail) {
        List<Reservation> reservationList = clientService.getUpcomingReservations(userEmail);
        return ReservationMapper.map(reservationList);
    }

    @GetMapping("/past/home/{userEmail}")
    public ResponseEntity<Collection<ReservationDto>> GetPastVacationHomeReservations(@PathVariable String userEmail) {
        List<Reservation> reservationList = clientService.getPastReservations(userEmail, ReservationType.VACATION_HOME);
        return new ResponseEntity<>(ReservationMapper.map(reservationList), HttpStatus.OK);
    }

    @GetMapping("/past/boat/{userEmail}")
    public ResponseEntity<Collection<ReservationDto>> GetPastBoatReservations(@PathVariable String userEmail) {
        List<Reservation> reservationList = clientService.getPastReservations(userEmail, ReservationType.BOAT);
        return new ResponseEntity<>(ReservationMapper.map(reservationList), HttpStatus.OK);
    }

    @GetMapping("/past/adventure/{userEmail}")
    public ResponseEntity<Collection<ReservationDto>> GetPastAdventureReservations(@PathVariable String userEmail) {
        List<Reservation> reservationList = clientService.getPastReservations(userEmail, ReservationType.ADVENTURE);
        return new ResponseEntity<>(ReservationMapper.map(reservationList), HttpStatus.OK);
    }

    @PostMapping("/cancel/{userEmail}")
    public ResponseEntity<Collection<ReservationDto>> CancelUpcomingReservation(@PathVariable String userEmail, @RequestBody Long reservationId) {
        Reservation reservation = clientService.cancelUpcomingReservation(reservationId, userEmail);
        List<Reservation> reservationList = clientService.getUpcomingReservations(userEmail);

        if (reservation == null) {
            return new ResponseEntity<>(ReservationMapper.map(reservationList), HttpStatus.CONFLICT);
        }
        switch (reservation.getType()) {
            case ADVENTURE:
                Adventure adventure = adventureService.getAdventureForReservation(reservationId);
                instructorService.addAvailabilityPeriod(new InstructorAvailability(reservation.getStartDate(), reservation.getEndDate()), adventure.getInstructor().getEmail());
                break;
            case VACATION_HOME:
                VacationHome vacationHome = homeService.getVacationHomeForReservation(reservationId);
                homeService.addAvailabilityPeriod(new VacationHomeAvailability(reservation.getStartDate(), reservation.getEndDate()), vacationHome.getId());
                break;
            case BOAT:
                Boat boat = boatService.getBoatForReservation(reservationId);
                boatService.addAvailabilityPeriod(new BoatAvailability(reservation.getStartDate(), reservation.getEndDate()), boat.getId());
                //boatOwnerService.add(reservation.getStartDate(), reservation.getEndDate(), boat.getBoatOwner().getEmail());

                break;
            default:
                break;
        }

        return ResponseEntity.ok(ReservationMapper.map(reservationList));
    }

    @GetMapping("/adventure/{ReservationId}")
    public AdventureDto GetAdventureForReservation(@PathVariable Long ReservationId) {
        return AdventureMapper.mapToDto(adventureService.getAdventureForReservation(ReservationId));
    }

    @GetMapping("/vacation/home/{ReservationId}")
    public VacationHomeDto GetVacationHomeForReservation(@PathVariable Long ReservationId) {
        return VacationHomeMapper.map(homeService.getVacationHomeForReservation(ReservationId));
    }

    @GetMapping("/boat/{ReservationId}")
    public BoatDto GetBoatForReservation(@PathVariable Long ReservationId) {
        return BoatMapper.mapToDto(boatService.getBoatForReservation(ReservationId));
    }


    @GetMapping("check-if-ongoing/{id}")
    public ResponseEntity<ClientDto> checkIfReservationIsOngoing(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation.getStartDate().before(new Date()) && reservation.getEndDate().after(new Date())) {
            return new ResponseEntity<>(ClientMapper.map(reservation.getClient()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
