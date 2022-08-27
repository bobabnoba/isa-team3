package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.ReservationWithClientDto;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("{id}")
    public ResponseEntity<ReservationWithClientDto> getReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(ReservationMapper.toDtoWClient(reservation));
    }


}
