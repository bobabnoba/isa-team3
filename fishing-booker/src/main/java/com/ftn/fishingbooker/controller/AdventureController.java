package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.AddressMapper;
import com.ftn.fishingbooker.mapper.AdventureMapper;
import com.ftn.fishingbooker.mapper.RentalMapper;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.*;
import com.ftn.fishingbooker.util.FIleUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/adventures")
@RequiredArgsConstructor
public class AdventureController {

    private final AdventureService adventureService;
    private final ClientService clientService;
    private final ReservationService reservationService;
    private final InstructorService instructorService;
    private final DateService dateService;
    private final SpecialOfferService specialOfferService;
    private final EarningsService earningsService;
    private final EmailService emailService;


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Collection<AdventureDto>> getAllAdventures() {
        Collection<Adventure> found = adventureService.getAll();

        Collection<AdventureDto> dtos = found.stream()
                .map(AdventureMapper::mapToDto)
                .collect(Collectors.toList());

        return ok(dtos);
    }

    @GetMapping("/by-instructor/{email}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Collection<AdventureDto>> getAllAdventuresByInstructor(@PathVariable String email) {
        Collection<Adventure> found = adventureService.getAllByInstructorEmail(email);

        Collection<AdventureDto> dtos = found.stream()
                .map(AdventureMapper::mapToDto)
                .collect(Collectors.toList());

        return ok(dtos);
    }

    @GetMapping("/by-instructor-id/{id}")
    public ResponseEntity<Collection<AdventureDto>> getAllAdventuresByInstructor(@PathVariable Long id) {
        Collection<Adventure> found = adventureService.findAllByInstructorId(id);

        Collection<AdventureDto> dtos = found.stream()
                .map(AdventureMapper::mapToDto)
                .collect(Collectors.toList());

        return ok(dtos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' ,'INSTRUCTOR', 'CLIENT')")
    public ResponseEntity<AdventureDto> getAdventureById(@PathVariable Long id) {
        Adventure found = adventureService.getById(id);
        AdventureDto dto = AdventureMapper.mapToDto(found);
        return ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Void> deleteAdventure(@PathVariable Long id) {
        adventureService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<AdventureDto> addNewAdventure(@RequestBody NewAdventureDto dto) {
        Adventure adventure = AdventureMapper.toEntity(dto);
        Adventure saved = adventureService.addAdventure(adventure, dto.getInstructorEmail());
        return ok(AdventureMapper.mapToDto(saved));
    }

    @PostMapping("/info-update/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<AdventureDto> updateAdventureInfo(@PathVariable Long id, @RequestBody AdventureInfo updated) {
        Adventure saved = adventureService.updateAdventureInfo(id, updated);
        return ResponseEntity.ok(AdventureMapper.mapToDto(saved));
    }

    @PostMapping("/additional-update/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<AdventureDto> updateAdventureAdditionalInfo(@PathVariable Long id, @RequestBody AdventureAdditionalInfo updated) {
        Adventure saved = adventureService.updateAdventureAdditionalInfo(id, updated);
        return ResponseEntity.ok(AdventureMapper.mapToDto(saved));
    }

    @PostMapping("/code-of-conduct-update/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<AdventureDto> updateAdventureCodeOfConduct(@PathVariable Long id, @RequestBody Collection<Rule> updated) {
        Adventure saved = adventureService.updateAdventureRules(id, updated);
        return ResponseEntity.ok(AdventureMapper.mapToDto(saved));
    }

    @PostMapping("/address-update/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<AdventureDto> updateAdventureAddress(@PathVariable Long id, @RequestBody AddressDto updated) {
        Adventure saved = adventureService.updateAdventureAddress(id, AddressMapper.toEntity(updated));
        return ResponseEntity.ok(AdventureMapper.mapToDto(saved));
    }

    @PostMapping("/image-upload/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Object> uploadImages(@RequestParam MultipartFile file, @PathVariable Long id) throws IOException {

        String uploadDir = "images/adventures/" + id;

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FIleUploadUtil.saveFile(uploadDir, fileName, file);
        adventureService.addImage(id, fileName);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/search")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Collection<RentalDto>> FilterAll(@RequestBody FilterDto filter) {
        if (clientService.hasOverlappingReservation(filter.getEmail(), filter.getStartDate(), filter.getEndDate())) {

            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Collection<Adventure> adventures = adventureService.filterAll(filter);
        Collection<RentalDto> rentals = RentalMapper.mapAdventureToRental(adventures);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @PostMapping("/rent/special/offer/{adventureId}/{offerId}/{userEmail}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ReservationDto> makeSpecialOfferReservation(@PathVariable String userEmail, @PathVariable Long offerId, @PathVariable Long adventureId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);

        if (client.getNoOfPenalties() >= 3) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Adventure adventure = adventureService.getById(adventureId);
        Date newEndDate = dateService.addHoursToJavaUtilDate(reservationDto.getStartDate(), adventure.getDurationInHours());
        reservationDto.setEndDate(newEndDate);
        reservationDto.setType(ReservationType.ADVENTURE);

        if (clientService.hasOverlappingReservation(userEmail, reservationDto.getStartDate(), reservationDto.getEndDate())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        Reservation reservation = reservationService.makeSpecialOfferReservation(client, reservationDto);
        adventureService.makeReservation(adventureId, reservation);
        instructorService.updateAvailability(new InstructorAvailability(reservation.getStartDate(), reservation.getEndDate()), adventure.getInstructor().getEmail());
        specialOfferService.reserveSpecialOffer(offerId);
        clientService.updatePoints(client, reservation.getPrice());
        instructorService.updatePoints(adventure.getInstructor(), reservation.getPrice());
        emailService.sendReservationEmail(ReservationMapper.map(reservation), client);
        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);
    }

    @PostMapping("/rent/{adventureId}/{userEmail}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ReservationDto> makeReservation(@PathVariable String userEmail, @PathVariable Long adventureId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);
        if (client.getNoOfPenalties() >= 3) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        reservationDto.setType(ReservationType.ADVENTURE);
        Reservation reservation = reservationService.makeAdventureReservation(client, adventureId, reservationDto);

        emailService.sendReservationEmail(ReservationMapper.map(reservation), client);
        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);

    }

    @PostMapping("/instructor-rent/{adventureId}/{userEmail}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<ReservationDto> instructorMakeReservation(@PathVariable String userEmail, @PathVariable Long adventureId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);
        if (client.getNoOfPenalties() >= 3) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Adventure adventure = adventureService.getById(adventureId);
        reservationDto.setType(ReservationType.ADVENTURE);
        Reservation reservation = reservationService.makeReservation(client, reservationDto, adventure.getDurationInHours());
        adventureService.makeReservation(adventureId, reservation);
        instructorService.updateAvailability(new InstructorAvailability(reservation.getStartDate(), reservation.getEndDate()), adventure.getInstructor().getEmail());
        clientService.updatePoints(client, reservation.getPrice());
        instructorService.updatePoints(adventure.getInstructor(), reservation.getPrice());
        earningsService.saveEarnings(reservation, adventure.getInstructor().getEmail(), adventure.getInstructor().getRank());
        emailService.sendReservationEmail(ReservationMapper.map(reservation), client);
        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);
    }


    @GetMapping("{id}/has-incoming-reservations")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Boolean> adventureHasIncomingReservations(@PathVariable Long id){
        return ResponseEntity.ok(adventureService.getNoOfIncomingReservations(id) > 0);
    }
}
