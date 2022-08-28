package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.AdventureMapper;
import com.ftn.fishingbooker.mapper.BoatMapper;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.mapper.VacationHomeMapper;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.ClientReview;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ClientService clientService;
    private final ClientReviewService clientReviewService;
    private final AdventureService adventureService;
    private final HomeService homeService;
    private final BoatService boatService;


    @GetMapping("{id}")
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
        boolean isCanceled = clientService.cancelUpcomingReservation(reservationId, userEmail);
        List<Reservation> reservationList = clientService.getUpcomingReservations(userEmail);
        if (isCanceled == true) {
            return ResponseEntity.ok(ReservationMapper.map(reservationList));
        }
        return new ResponseEntity<>(ReservationMapper.map(reservationList), HttpStatus.CONFLICT);
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

    @PostMapping("/leave/review/{userEmail}")
    public ResponseEntity<Collection<ReservationDto>> LeaveReview(@PathVariable String userEmail, @RequestBody ClientReviewDto clientReviewDto) {
        Client client = clientService.getClientByEmail(userEmail);
        ClientReview clientReview = clientReviewService.newClientReview(client, clientReviewDto);

        reservationService.leaveReview(clientReviewDto.getReservationId(), clientReview);

        List<Reservation> reservationList = clientService.getPastReservations(userEmail, ReservationType.VACATION_HOME);
        return new ResponseEntity<>(ReservationMapper.map(reservationList), HttpStatus.CREATED);
    }

}
