package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.*;
import com.ftn.fishingbooker.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

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
    private final InstructorService instructorService;
    private final BoatOwnerService boatOwnerService;

    private final HomeOwnerService homeOwnerService;


    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('HOME_OWNER', 'BOAT_OWNER', 'INSTRUCTOR')")
    public ResponseEntity<ReservationWithClientDto> getReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ok(ReservationMapper.toDtoWClient(reservation));
    }

    @GetMapping("/client/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public ResponseEntity<Reservation> GetReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ok(reservation);
    }

    @GetMapping("/upcoming/{userEmail}")
    @PreAuthorize("hasRole('CLIENT')")
    public Collection<ReservationDto> GetUpcomingReservations(@PathVariable String userEmail) {
        List<Reservation> reservationList = clientService.getUpcomingReservations(userEmail);
        return ReservationMapper.map(reservationList);
    }

    @GetMapping("/past/home/{userEmail}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Collection<ReservationDto>> GetPastVacationHomeReservations(@PathVariable String userEmail) {
        List<Reservation> reservationList = clientService.getPastReservations(userEmail, ReservationType.VACATION_HOME);
        return new ResponseEntity<>(ReservationMapper.map(reservationList), HttpStatus.OK);
    }

    @GetMapping("/past/boat/{userEmail}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Collection<ReservationDto>> GetPastBoatReservations(@PathVariable String userEmail) {
        List<Reservation> reservationList = clientService.getPastReservations(userEmail, ReservationType.BOAT);
        return new ResponseEntity<>(ReservationMapper.map(reservationList), HttpStatus.OK);
    }

    @GetMapping("/past/adventure/{userEmail}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Collection<ReservationDto>> GetPastAdventureReservations(@PathVariable String userEmail) {
        List<Reservation> reservationList = clientService.getPastReservations(userEmail, ReservationType.ADVENTURE);
        return new ResponseEntity<>(ReservationMapper.map(reservationList), HttpStatus.OK);
    }

    @PostMapping("/cancel/{userEmail}")
    @PreAuthorize("hasRole('CLIENT')")
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
                if (reservation.isOwnerCaptain()) {
                    boatOwnerService.addAvailabilityPeriod(new BoatOwnerAvailability(reservation.getStartDate(), reservation.getEndDate()), boat.getBoatOwner().getEmail());
                }
                break;
            default:
                break;
        }

        return ok(ReservationMapper.map(reservationList));
    }


    @GetMapping("/adventure/{ReservationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public AdventureDto GetAdventureForReservation(@PathVariable Long ReservationId) {
        return AdventureMapper.mapToDto(adventureService.getAdventureForReservation(ReservationId));
    }

    @GetMapping("/vacation/home/{ReservationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public VacationHomeDto GetVacationHomeForReservation(@PathVariable Long ReservationId) {
        return VacationHomeMapper.map(homeService.getVacationHomeForReservation(ReservationId));
    }

    @GetMapping("/boat/{ReservationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public BoatDto GetBoatForReservation(@PathVariable Long ReservationId) {
        return BoatMapper.mapToDto(boatService.getBoatForReservation(ReservationId));
    }


    @GetMapping("check-if-ongoing/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'CLIENT', 'BOAT_OWNER', 'HOME_OWNER')")
    public ResponseEntity<ClientDto> checkIfReservationIsOngoing(@PathVariable Long id) {

        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(timeZone);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        var t = cal.getTime();
        var now = t.getTime() - 2 * 3600 * 1000;
        var dateNow = new Timestamp(now);
        Reservation reservation = reservationService.getReservationById(id);
        dateNow.setNanos(0);
        if (reservation.getStartDate().before(dateNow) && (reservation.getEndDate().after(dateNow) || reservation.getEndDate().equals(dateNow))) {
            return new ResponseEntity<>(ClientMapper.map(reservation.getClient()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/chart/{type}")
    @PreAuthorize("hasAnyRole('BOAT_OWNER', 'HOME_OWNER', 'INSTRUCTOR')")
    public ResponseEntity<Collection<EarningsChartDto>> getReservationChartInDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            @RequestParam(required = false) Long id, @RequestParam(required = false) String email,
            @PathVariable ReservationType type) {

        Set<Reservation> found = new HashSet<>();
        if (type.equals(ReservationType.BOAT)) {
            if (email != null) {
                BoatOwner owner = boatOwnerService.getByEmail(email);
                var s = reservationService.getReservationForBoatOwnerChart(owner.getId(), from, to);
                found.addAll(s);
            } else {
                found.addAll(reservationService.getReservationsForBoatChart(id, from, to));
            }
        } else if (type.equals(ReservationType.VACATION_HOME)) {
            if (email != null) {
                HomeOwner owner = homeOwnerService.getByEmail(email);
                found.addAll(reservationService.getReservationForHomeOwnerChart(owner.getId(), from, to));
            } else {
                found.addAll(reservationService.getReservationsForHomeChart(id, from, to));
            }
        } else {
            if (email != null) {
                Instructor instructor = instructorService.findByEmail(email);
                found.addAll(reservationService.getReservationForInstructorChart(instructor.getId(), from, to));
            } else {
                found.addAll(reservationService.getReservationsForAdventureChart(id, from, to));
            }
        }
        Collection<EarningsChartDto> earningsDtos = new ArrayList<>();
        for (Date d = from; d.before(to); d = DateUtil.addDays(d, 1)) {
            Date finalD = new Date(d.getTime());
            Collection<Reservation> dtoDay = found.stream().filter(earnings ->
                    (earnings.getStartDate().after(finalD) || earnings.getStartDate().getTime() == finalD.getTime()) &&
                            earnings.getStartDate().before(DateUtil.addDays(finalD, 1))).collect(Collectors.toSet());
            EarningsChartDto dto = new EarningsChartDto();
            dto.setDate(finalD);
            dto.setIncome((double) dtoDay.size());
            earningsDtos.add(dto);
        }
        return ok(earningsDtos);
    }

    @GetMapping("/chart-year/{type}")
    @PreAuthorize("hasAnyRole('BOAT_OWNER', 'HOME_OWNER', 'INSTRUCTOR')")
    public ResponseEntity<Collection<ReservationChartDto>> getReservationChartYearInDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            @RequestParam(required = false) Long id, @RequestParam(required = false) String email,
            @PathVariable ReservationType type) {

        Set<Reservation> found = new HashSet<>();
        if (type.equals(ReservationType.BOAT)) {
            if (email != null) {
                BoatOwner owner = boatOwnerService.getByEmail(email);
                found.addAll(reservationService.getReservationForBoatOwnerChart(owner.getId(), from, to));
            } else {
                found.addAll(reservationService.getReservationsForBoatChart(id, from, to));
            }
        } else if (type.equals(ReservationType.VACATION_HOME)) {
            if (email != null) {
                HomeOwner owner = homeOwnerService.getByEmail(email);
                found.addAll(reservationService.getReservationForHomeOwnerChart(owner.getId(), from, to));
            } else {
                found.addAll(reservationService.getReservationsForHomeChart(id, from, to));
            }
        } else {
            if (email != null) {
                Instructor instructor = instructorService.findByEmail(email);
                found.addAll(reservationService.getReservationForInstructorChart(instructor.getId(), from, to));
            } else {
                found.addAll(reservationService.getReservationsForAdventureChart(id, from, to));
            }
        }
        Collection<ReservationChartDto> earningsDtos = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (int i = 1; i < 13; i++) {
            int sum = 0;
            for (Reservation r : found) {
                var m = cal.get(Calendar.MONTH);
                cal.setTime(new Date(r.getStartDate().getTime()));
                if (cal.get(Calendar.MONTH) == i) {
                    sum++;
                }
            }
            ReservationChartDto dto = new ReservationChartDto();
            dto.setMonth(i);
            dto.setNumOfRes(sum);
            earningsDtos.add(dto);
        }
        return ok(earningsDtos);
    }

}
