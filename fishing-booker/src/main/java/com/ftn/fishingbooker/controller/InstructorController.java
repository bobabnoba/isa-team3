package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.InstructorAvailability;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.dao.ReservationInfo;
import com.ftn.fishingbooker.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody OwnerRegisterDto registerDto) throws MessagingException {

        Instructor instructor = RegistrationMapper.mapToInstructor(registerDto);
        User user = instructorService.register(instructor, registerDto.motivation);
        return new ResponseEntity<>(UserMapper.mapToDto(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<InstructorDto> getInstructorWithAvailability(String email) {
        Instructor found = instructorService.getWithAvailability(email);
        return ok(InstructorMapper.toDto(found));
    }

    @PostMapping("/add-availability")
    public ResponseEntity<InstructorAvailabilityResponseDto> addAvailabilityPeriod(@RequestBody InstructorAvailabilityRequestDto availability) {
        InstructorAvailability saved = instructorService.addAvailabilityPeriod(InstructorAvailabilityMapper.mapToEntity(availability), availability.getInstructorEmail());
        return ok(InstructorAvailabilityMapper.mapToResponse(saved));
    }

    @GetMapping("/available")
    public Collection<InstructorBrowseDto> GetAll() {
        Collection<Instructor> instructors = instructorService.getAll();

        return InstructorMapper.mapInstructors(instructors);
    }

    @GetMapping("/check-if-available")
    ResponseEntity<Boolean> checkAvailability(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date to,
                                              @RequestParam String instructorEmail) {
        return ok(instructorService.checkAvailability(from, to, instructorEmail));
    }

    @GetMapping("/reservations/upcoming")
    ResponseEntity<Collection<ReservationInfo>> getUpcomingReservations(@RequestParam String instructorEmail) {
        Collection<ReservationInfo> reservations = instructorService.getUpcomingReservationsForInstructor(instructorEmail);
//        Collection<ReservationDto> dtos = reservations.stream()
//                .map(ReservationMapper::map)
//                .collect(Collectors.toList());
        return ok(reservations);
    }

    @GetMapping("/reservations/past")
    ResponseEntity<Collection<ReservationDto>> getReservationsHistory(@RequestParam String instructorEmail) {
        Collection<Reservation> reservations = instructorService.getPastReservationsForInstructor(instructorEmail);
        Collection<ReservationDto> dtos = reservations.stream()
                .map(ReservationMapper::map)
                .collect(Collectors.toList());
        return ok(dtos);
    }
}
