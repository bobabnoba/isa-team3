package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.EmailService;
import com.ftn.fishingbooker.service.HomeService;
import com.ftn.fishingbooker.service.ReservationService;
import com.ftn.fishingbooker.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/vacation/homes")
@RequiredArgsConstructor
public class HomeController {
    private final HomeService vacationHomeService;
    private final ClientService clientService;
    private final ReservationService reservationService;
    private final EmailService emailService;

    @GetMapping("/{id}")
        public ResponseEntity<VacationHomeDto> getHomeById(@PathVariable Long id) {
        VacationHome found = vacationHomeService.getById(id);
        VacationHomeDto dto = VacationHomeMapper.map(found);
        return ok(dto);
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
    @GetMapping("/profile/{id}")
    public ResponseEntity<VacationHomeDto> getHomeProfileById(@PathVariable Long id) {
        VacationHome found = vacationHomeService.getById(id);
        VacationHomeDto dto = VacationHomeMapper.mapToHomeOwnerDto(found);
        return ok(dto);
    }

    @PostMapping("/rent/{homeId}/{userEmail}")
    public ResponseEntity<ReservationDto> makeReservation(@PathVariable String userEmail, @PathVariable Long homeId, @RequestBody ReservationDto reservationDto) {
        Client client = clientService.getClientByEmail(userEmail);
        if (client.getNoOfPenalties() >= 3){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        reservationDto.setType(ReservationType.VACATION_HOME);
        Reservation reservation = reservationService.makeReservation(client, reservationDto);
        vacationHomeService.makeReservation(homeId, reservation);
        clientService.updatePoints(client, reservation.getPrice());
        //emailService.sendReservationEmail(ReservationMapper.map(reservation), client);
        return new ResponseEntity<>(ReservationMapper.map(reservation), HttpStatus.OK);
    }

    @GetMapping("/by-owner/{email}")
    public ResponseEntity<Collection<VacationHomeDto>> getAllHomesByOwner(@PathVariable String email) {
        Collection<VacationHome> found = vacationHomeService.getAllByOwner(email);
        Collection<VacationHomeDto> dtos = found.stream()
                .map(VacationHomeMapper::mapToHomeOwnerDto)
                .collect(Collectors.toList());

        return ok(dtos);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHome(@PathVariable Long id) {
        vacationHomeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<VacationHomeDto> addNewHome(@RequestBody NewHomeDto dto) {
        VacationHome home = VacationHomeMapper.toEntity(dto);
        VacationHome saved = vacationHomeService.addHome(home, dto.getOwnerEmail());
        return ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }
    @PostMapping("/info-update/{id}")
    public ResponseEntity<VacationHomeDto> updateHomeInfo(@PathVariable Long id, @RequestBody HomeInfoDto updated) {
        VacationHome saved = vacationHomeService.updateHomeInfo(id, updated);
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/additional-update/{id}")
    public ResponseEntity<VacationHomeDto> updateHomeAdditionalInfo(@PathVariable Long id, @RequestBody HomeAdditionalInfo updated) {
        VacationHome saved = vacationHomeService.updateHomeAdditionalInfo(id, updated);
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/code-of-conduct-update/{id}")
    public ResponseEntity<VacationHomeDto> updateHomeCodeOfConduct(@PathVariable Long id, @RequestBody Collection<Rule> updated) {
        VacationHome saved = vacationHomeService.updateHomeRules(id, updated);
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/address-update/{id}")
    public ResponseEntity<VacationHomeDto> updateHomeAddress(@PathVariable Long id, @RequestBody AddressDto updated) {
        VacationHome saved = vacationHomeService.updateHomeAddress(id, AddressMapper.toEntity(updated));
        return ResponseEntity.ok(VacationHomeMapper.mapToHomeOwnerDto(saved));
    }

    @PostMapping("/image-upload/{id}")
    public ResponseEntity<Object> uploadImages(@RequestParam MultipartFile file, @PathVariable Long id) throws IOException {

        String uploadDir = "images/homes/" + id;

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FIleUploadUtil.saveFile(uploadDir, fileName, file);
        vacationHomeService.addImage(id, fileName);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-availability")
    public ResponseEntity<?> addAvailabilityPeriod(@RequestBody HomeAvailabilityRequestDto availability) {
        VacationHomeAvailability saved = vacationHomeService.addAvailabilityPeriod(VacationHomeMapper.mapToHomeAvailabilityEntity(availability), availability.getHomeId());
        if (saved != null){
            return ok(VacationHomeMapper.mapToAvailabilityDto(saved));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check-if-available")
    ResponseEntity<Boolean> checkAvailability(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date to,
                                              @RequestParam Long homeId) {
        return ok(vacationHomeService.checkAvailability(from, to, homeId));
    }

    @GetMapping("for-reservation/{reservationId}")
    public ResponseEntity<HomeInfoDto> getHomeForReservation(@PathVariable Long reservationId){
        VacationHome home = vacationHomeService.getHomeForReservation(reservationId);
        return new ResponseEntity<>(VacationHomeMapper.mapToDtoInfo(home),HttpStatus.OK);
    }
}

