package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dao.ReservationCalendarInfo;
import com.ftn.fishingbooker.dao.SpecialOfferCalendarInfo;
import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.dao.ReservationInfo;
import com.ftn.fishingbooker.service.*;
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
    private final UserService userService;
    private final ReservationService reservationService;
    private final SpecialOfferService specialOfferService;

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
    @GetMapping("/info")
    public ResponseEntity<InstructorInfoDto> getInstructorWithAvailability(Long id) {
        Instructor found = instructorService.getWithAvailabilityById(id);
        return ok(InstructorMapper.mapToInstructorInfo(found));
    }

    @PostMapping("/add-availability")
    public ResponseEntity<Collection<InstructorAvailabilityResponseDto>> addAvailabilityPeriod(@RequestBody InstructorAvailabilityRequestDto availability) {
        Collection<InstructorAvailability> saved = instructorService.addAvailabilityPeriod(InstructorAvailabilityMapper.mapToEntity(availability), availability.getInstructorEmail());
        Collection<InstructorAvailabilityResponseDto> dtos = saved.stream()
                .map(InstructorAvailabilityMapper::mapToResponse)
                .collect(Collectors.toList());
        return ok(dtos);
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

    @GetMapping("/has-overlapping-reservation")
    public ResponseEntity<Boolean> checkIfAvailable(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date to,
                                                    @RequestParam String email) {
        return ResponseEntity.ok(instructorService.hasOverlappingReservation(email, from, to));
    }

    @GetMapping("ongoing-reservation-client/{email}")
    public ResponseEntity<UserDto> getOngoingReservationClient(@PathVariable String email) {
        Reservation reservation = instructorService.getOngoingReservationForInstructor(email);
        if (reservation != null) {
            return ResponseEntity.ok(UserMapper.mapToDto(userService.getUserById(reservation.getClient().getId())));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/reservations/{email}")
    ResponseEntity<Collection<ReservationCalendarInfo>> getAllReservations(@PathVariable String email) {
        Instructor instructor = instructorService.findByEmail(email);
        Collection<ReservationCalendarInfo> reservations = reservationService.getAllInstructorReservations(instructor.getId());
        return ok(reservations);
    }

    @GetMapping("special-offers/{email}")
    ResponseEntity<Collection<SpecialOfferCalendarInfo>> getInstructorSpecialOffers(@PathVariable String email) {
        Instructor instructor = instructorService.findByEmail(email);
        Collection<SpecialOfferCalendarInfo> found = specialOfferService.getAllInstructorsOffers(instructor.getId());
        return ResponseEntity.ok(found);
    }

    @PostMapping("/delete-availability")
    public ResponseEntity<Collection<InstructorAvailabilityResponseDto>> deleteAvailabilityPeriod(@RequestBody InstructorAvailabilityRequestDto dto) {
        Collection<InstructorAvailability> avs = instructorService.deleteAvailability(new InstructorAvailability(dto.getStartDate(),dto.getEndDate()), dto.getInstructorEmail());
        Collection<InstructorAvailability> availabilities = instructorService.getWithAvailability(dto.getInstructorEmail()).getAvailability();
                Collection<InstructorAvailabilityResponseDto> dtos = availabilities.stream()
                .map(InstructorAvailabilityMapper::mapToResponse)
                .collect(Collectors.toList());
        return ok(dtos);
    }

}

