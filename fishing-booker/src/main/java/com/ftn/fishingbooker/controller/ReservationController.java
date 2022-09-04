package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.*;
import com.ftn.fishingbooker.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ClientService clientService;
    private final AdventureService adventureService;
    private final HomeService homeService;
    private final BoatService boatService;


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



    @GetMapping("check-if-ongoing/{id}")
    public ResponseEntity<ClientDto> checkIfReservationIsOngoing(@PathVariable Long id){
        Reservation reservation = reservationService.getReservationById(id);
        if(reservation.getStartDate().before(new Date()) && reservation.getEndDate().after(new Date())){
            return new ResponseEntity<>(ClientMapper.map(reservation.getClient()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/chart/{id}/{type}")
    public ResponseEntity<Collection<EarningsChartDto>> getReservationChartInDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            @PathVariable Long id, @PathVariable ReservationType type) {

        Set<Reservation> found = new HashSet<>();
        if(type.equals(ReservationType.BOAT)){
            found.addAll(reservationService.getReservationsForBoat(id));
        }else if( type.equals(ReservationType.VACATION_HOME)){
            found.addAll(reservationService.getReservationsForHome(id));
        }else{
            found.addAll(reservationService.getReservationsForAdventure(id));
        }
        Collection<EarningsChartDto> earningsDtos = new ArrayList<>();
        for (Date d = from; d.before(to); d = DateUtil.addDays(d,1)){
            Date finalD = new Date(d.getTime() - 1);
            Collection<Reservation> dtoDay = found.stream().filter(earnings ->
                earnings.getStartDate().after(finalD) &&
                        earnings.getStartDate().before(DateUtil.addDays(finalD,1))).collect(Collectors.toSet());
            EarningsChartDto dto = new EarningsChartDto();
            dto.setDate(finalD);
            dto.setIncome((double) dtoDay.size());
            earningsDtos.add(dto);
        }
        return ok(earningsDtos);
    }

    @GetMapping("/chart-year/{id}/{type}")
    public ResponseEntity<Collection<EarningsChartDto>> getReservationChartYearInDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            @PathVariable Long id, @PathVariable ReservationType type) {

        Set<Reservation> found = new HashSet<>();
        if(type.equals(ReservationType.BOAT)){
            found.addAll(reservationService.getReservationsForBoat(id));
        }else if( type.equals(ReservationType.VACATION_HOME)){
            found.addAll(reservationService.getReservationsForHome(id));
        }else{
            found.addAll(reservationService.getReservationsForAdventure(id));
        }
        Collection<EarningsChartDto> earningsDtos = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(from);
//        int month = cal.get(Calendar.MONTH);
        for (Date d = from; d.before(to); d = DateUtil.addDays(d, 30)){
            Date finalD = new Date(d.getTime() - 1);
            Collection<Reservation> dtoDay = found.stream().filter(earnings ->
                    earnings.getStartDate().after(finalD) &&
                            earnings.getStartDate().before(DateUtil.addDays(finalD,30))).collect(Collectors.toSet());
            EarningsChartDto dto = new EarningsChartDto();
            dto.setDate(finalD);
            dto.setIncome((double) dtoDay.size());
            earningsDtos.add(dto);
        }

        Collection<EarningsChartDto> months = new ArrayList<>();
        EarningsChartDto dto = new EarningsChartDto();


        return ok(earningsDtos);
    }

}
