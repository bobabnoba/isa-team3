package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.FilterDto;
import com.ftn.fishingbooker.dto.RentalDto;
import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.dto.VacationHomeDto;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.RentalMapper;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.mapper.VacationHomeMapper;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.EmailService;
import com.ftn.fishingbooker.service.HomeService;
import com.ftn.fishingbooker.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/vacation/homes")
@RequiredArgsConstructor
public class HomeController {
    private final HomeService vacationHomeService;
    private final ClientService clientService;
    private final ReservationService reservationService;
    private final EmailService emailService;

    @GetMapping("/{id}")
    public VacationHomeDto GetVacationHome(@PathVariable("id") Long id) {
        VacationHome home = vacationHomeService.getById(id);

        return VacationHomeMapper.map(home);
    }

    @GetMapping()
    public Collection<RentalDto> GetAll() {
        Collection<VacationHome> homes = vacationHomeService.getAll();

        return RentalMapper.mapVacationHomeToRental(homes);
    }

    @PostMapping("/search")
    public ResponseEntity<Collection<RentalDto>> FilterAll(@RequestBody FilterDto filter) {
        if (clientService.hasOverlappingReservation(filter.getEmail(), filter.getStartDate(), filter.getEndDate())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Collection<VacationHome> vacationHomes = vacationHomeService.filterAll(filter);
        Collection<RentalDto> rentals = RentalMapper.mapVacationHomeToRental(vacationHomes);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("/reservations/{homeId}")
    public Collection<ReservationDto> GetVacationHomeReservations(@PathVariable Long homeId) {

        return ReservationMapper.map(reservationService.getReservationForVacationHome(homeId));
    }

    @PostMapping("/rent/{homeId}/{userEmail}")
    public ResponseEntity<ReservationDto> makeReservation(@PathVariable String userEmail, @PathVariable Long homeId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);

        reservationDto.setType(ReservationType.VACATION_HOME);
        Reservation reservation = reservationService.makeReservation(client, reservationDto);
        vacationHomeService.makeReservation(homeId, reservation);
        clientService.updatePoints(client, reservation.getPrice());
        emailService.sendReservationEmail(ReservationMapper.map(reservation), client);
        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);
    }
}

