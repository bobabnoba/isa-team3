package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.AddressMapper;
import com.ftn.fishingbooker.mapper.BoatMapper;
import com.ftn.fishingbooker.mapper.RentalMapper;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.BoatService;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.ReservationService;
import com.ftn.fishingbooker.util.FIleUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/boats")
@RequiredArgsConstructor
public class BoatController {

    private final BoatService boatService;
    private final ClientService clientService;
    private final ReservationService reservationService;

    @GetMapping()
    public Collection<RentalDto> GetAll() {
        Collection<Boat> boats = boatService.getAll();

        return RentalMapper.mapBoatToRental(boats);
    }

    @PostMapping("/search")
    public ResponseEntity<Collection<RentalDto>> FilterAll(@RequestBody FilterDto filter) {
        if (clientService.hasOverlappingReservation(filter.getEmail(), filter.getStartDate(), filter.getEndDate())) {

            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Collection<Boat> boats = boatService.filterAll(filter);
        Collection<RentalDto> rentals = RentalMapper.mapBoatToRental(boats);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @PostMapping("/rent/{boatId}/{userEmail}")
    public ResponseEntity<ReservationDto> makeReservation(@PathVariable String userEmail, @PathVariable Long boatId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);
        if (client.getNoOfPenalties() >= 3) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        reservationDto.setType(ReservationType.BOAT);
        Reservation reservation = reservationService.makeReservation(client, reservationDto);
        boatService.makeReservation(boatId, reservation);
        clientService.updatePoints(client, reservation.getPrice());

        //emailService.sendReservationEmail(ReservationMapper.map(reservation), client);
        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);
    }

    @PostMapping("/owner-rent/{boatId}/{userEmail}")
    public ResponseEntity<ReservationDto> ownerMakeReservation(@PathVariable String userEmail, @PathVariable Long boatId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);
        if (client.getNoOfPenalties() >= 3) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        reservationDto.setType(ReservationType.BOAT);
        Reservation reservation = reservationService.ownerMakeReservation(client, reservationDto);
        boatService.makeReservation(boatId, reservation);
        clientService.updatePoints(client, reservation.getPrice());

        //TODO: send mail
        //emailService.sendReservationEmail(ReservationMapper.map(reservation), client);
        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);
    }

    @GetMapping("/by-owner/{email}")
    public ResponseEntity<Collection<BoatDto>> getAllBoatsByOwner(@PathVariable String email) {
        Collection<Boat> found = boatService.getAllByOwner(email);

        Collection<BoatDto> dtos = found.stream()
                .map(BoatMapper::mapToDtoWithAvailability)
                .collect(Collectors.toList());

        return ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoatDto> getBoatById(@PathVariable Long id) {
        Boat found = boatService.getById(id);
        BoatDto dto = BoatMapper.mapToDtoWithAvailability(found);
        return ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteABoat(@PathVariable Long id) {
        boatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BoatDto> addNewBoat(@RequestBody NewBoatDto dto) {
        Boat boat = BoatMapper.toEntity(dto);
        Boat saved = boatService.addBoat(boat, dto.getOwnerEmail());
        return ok(BoatMapper.mapToDto(saved));
    }

    @PostMapping("/info-update/{id}")
    public ResponseEntity<BoatDto> updateBoatInfo(@PathVariable Long id, @RequestBody BoatInfo updated) {
        Boat saved = boatService.updateBoatInfo(id, updated);
        return ResponseEntity.ok(BoatMapper.mapToDto(saved));
    }

    @PostMapping("/additional-update/{id}")
    public ResponseEntity<BoatDto> updateBoatAdditionalInfo(@PathVariable Long id, @RequestBody BoatAdditionalInfo updated) {
        Boat saved = boatService.updateBoatAdditionalInfo(id, updated);
        return ResponseEntity.ok(BoatMapper.mapToDto(saved));
    }

    @PostMapping("/code-of-conduct-update/{id}")
    public ResponseEntity<BoatDto> updateBoatCodeOfConduct(@PathVariable Long id, @RequestBody Collection<Rule> updated) {
        Boat saved = boatService.updateBoatRules(id, updated);
        return ResponseEntity.ok(BoatMapper.mapToDto(saved));
    }

    @PostMapping("/address-update/{id}")
    public ResponseEntity<BoatDto> updateBoatAddress(@PathVariable Long id, @RequestBody AddressDto updated) {
        Boat saved = boatService.updateBoatAddress(id, AddressMapper.toEntity(updated));
        return ResponseEntity.ok(BoatMapper.mapToDto(saved));
    }

    @PostMapping("/image-upload/{id}")
    public ResponseEntity<Object> uploadImages(@RequestParam MultipartFile file, @PathVariable Long id) throws IOException {

        String uploadDir = "images/boats/" + id;

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FIleUploadUtil.saveFile(uploadDir, fileName, file);
        boatService.addImage(id, fileName);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/add-availability")
    public ResponseEntity<?> addAvailabilityPeriod(@RequestBody BoatAvailabilityRequestDto availability) {
        BoatAvailability saved = boatService.addAvailabilityPeriod(BoatMapper.mapToBoatAvailabilityEntity(availability), availability.boatId);
        if (saved != null){
            return ok(BoatMapper.mapToAvailabilityDto(saved));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check-if-available")
    ResponseEntity<Boolean> checkAvailability(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date to,
                                              @RequestParam Long boatId) {
        return ok(boatService.checkAvailability(from, to, boatId));
    }

    @GetMapping("for-reservation/{reservationId}")
    public ResponseEntity<BoatInfo> getBoatForReservation(@PathVariable Long reservationId){
        Boat boat = boatService.getBoatForReservation(reservationId);
        return new ResponseEntity<>(BoatMapper.mapToDtoInfo(boat),HttpStatus.OK);
    }

}
