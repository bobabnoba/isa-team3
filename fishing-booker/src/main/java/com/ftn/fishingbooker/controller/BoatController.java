package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.BoatService;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.ReservationService;
import com.ftn.fishingbooker.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.Collection;
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

        reservationDto.setType(ReservationType.BOAT);
        Reservation reservation = reservationService.makeReservation(client, reservationDto);
        boatService.makeReservation(boatId, reservation);
        clientService.updatePoints(client, reservation.getPrice());

        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);
    }

    @GetMapping("/by-owner/{email}")
    public ResponseEntity<Collection<BoatDto>> getAllBoatsByOwner(@PathVariable String email) {
        Collection<Boat> found = boatService.getAllByOwner(email);

        Collection<BoatDto> dtos = found.stream()
                .map(BoatMapper::mapToDto)
                .collect(Collectors.toList());

        return ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoatDto> getBoatById(@PathVariable Long id){
        Boat found = boatService.getById(id);
        BoatDto dto = BoatMapper.mapToDto(found);
        return ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteABoat(@PathVariable Long id){
        boatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BoatDto> addNewBoat(@RequestBody NewBoatDto dto) {
        Boat boat =  BoatMapper.toEntity(dto);
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

}
